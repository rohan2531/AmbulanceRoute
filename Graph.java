package amburoute;

import java.util.*;

public class Graph {
    private Map<String, List<Edge>> adjList = new HashMap<>();

    static class Edge {
        String to;
        int weight;
        Edge(String to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class PQNode {
        String id;
        long dist;
        PQNode(String id, long dist) {
            this.id = id;
            this.dist = dist;
        }
    }

    public static class PathResult {
        public final long distance;       
        public final List<String> path;  

        public PathResult(long distance, List<String> path) {
            this.distance = distance;
            this.path = Collections.unmodifiableList(path);
        }
    }

    public void addEdge(String from, String to, int weight) {
        adjList.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, weight));
        adjList.computeIfAbsent(to, k -> new ArrayList<>()).add(new Edge(from, weight)); 
    }

    public PathResult dijkstra(String start, String end) {
        if (start == null || end == null) return new PathResult(-1, Collections.emptyList());
        if (!adjList.containsKey(start)) return new PathResult(-1, Collections.emptyList());

        Map<String, Long> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        PriorityQueue<PQNode> pq = new PriorityQueue<>(Comparator.comparingLong(n -> n.dist));

        for (String node : adjList.keySet()) dist.put(node, Long.MAX_VALUE);
        dist.put(start, 0L);
        pq.add(new PQNode(start, 0L));

        while (!pq.isEmpty()) {
            PQNode cur = pq.poll();
            if (cur.dist > dist.getOrDefault(cur.id, Long.MAX_VALUE)) continue;
            if (cur.id.equals(end)) break; // early exit when destination popped

            List<Edge> neighbors = adjList.getOrDefault(cur.id, Collections.emptyList());
            for (Edge e : neighbors) {
                long nd = cur.dist + (long)e.weight;
                if (nd < dist.getOrDefault(e.to, Long.MAX_VALUE)) {
                    dist.put(e.to, nd);
                    prev.put(e.to, cur.id);
                    pq.add(new PQNode(e.to, nd));
                }
            }
        }

        if (!dist.containsKey(end) || dist.get(end) == Long.MAX_VALUE) {
            return new PathResult(-1, Collections.emptyList());
        }

        LinkedList<String> path = new LinkedList<>();
        for (String at = end; at != null; at = prev.get(at)) {
            path.addFirst(at);
            if (at.equals(start)) break;
        }
        if (path.isEmpty() || !path.getFirst().equals(start)) {
            return new PathResult(-1, Collections.emptyList());
        }

        return new PathResult(dist.get(end), path);
    }
}
