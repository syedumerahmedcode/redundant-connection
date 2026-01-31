public class Solution {

    public int[] findRedundantConnection(int[][] edges) {
        /**
         * We solve this problem using disjointed set union approach.
         * here, we treat all nodes as separates and create theie own groups,
                 * represented by their representative 
         * then we iterate over edges and merge them ,
         while iterating, if we find common parent then that will be the link cauing the cycle
         and we return that as our answer
         */

        /**
         * initiaize the parent array where parent[i] represents the node i
         */
        int[] parent = new int[edges.length + 1];
        // Start from 1. Why? Because it is given in the problem statement: "nodes labeled from 1 to n"
        for (int i = 1; i <= edges.length; i++) {
            //initially, each node is its own parent
            parent[i] = i;
        }

        /**
         * iterate through the edges to find the redundant link
         */
        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];

            /**
             * Find the roots of node1 and node2
             */
            int root1 = find(parent, node1);
            int root2 = find(parent, node2);

            /**
             * If the rrots are same, a cycle is detected. return the current edge
             */
            if (root1 == root2) {
                return edge;
            }
            // union the sets by making root1 the parent of root 2, basicyll the merge part of Disjointed Set Union data structure
            parent[root2] = root1;
        }
        // if no cycle is found, then return an empty array as a fallback.
        return new int[0];

    }
    
    /**
     * Helper method to find the root of a node
     */
    private  int find(int[] parent, int node) {
        while (node != parent[node]) {
            // path compression for optimization
            parent[node] = parent[parent[node]];
            node = parent[node];
        }
        return node;
    }

}
