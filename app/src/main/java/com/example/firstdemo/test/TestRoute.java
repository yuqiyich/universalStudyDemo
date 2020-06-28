package com.example.firstdemo.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class TestRoute {
    public static void main(String[] args) throws Exception {
        //初始化节点数据
        TreeNode A = new TreeNode("A");
        TreeNode B = new TreeNode("B");
        TreeNode C = new TreeNode("C");
        TreeNode D = new TreeNode("D");
        TreeNode E = new TreeNode("E");
        //初始化所有连线信息
        new TwoStraightNodeInfo(A, B, 5);
        new TwoStraightNodeInfo(A, D, 5);
        new TwoStraightNodeInfo(A, E, 7);
        new TwoStraightNodeInfo(B, C, 4);
        new TwoStraightNodeInfo(C, D, 8);
        new TwoStraightNodeInfo(C, E, 2);
        new TwoStraightNodeInfo(D, E, 6);
        new TwoStraightNodeInfo(D, C, 8);
        new TwoStraightNodeInfo(E, B, 3);


        calcMinDistance(A,D);

    }

    /**
     * startNode和endNode不相等
     *
     * @param startNode
     * @param endNode
     * @return
     */
    private static int calcMinDistance(TreeNode startNode, TreeNode endNode) {
        //获取所有可达的路劲
        ArrayList<LinkedList<TwoStraightNodeInfo>>  allLinkRoute=getRouteBetweenTwoNode(null,startNode,endNode);
        System.out.println(startNode.getName()+"到"+endNode.getName()+"所有有效路径如下：");
        int minDistance=calcLinkNodeDistance(allLinkRoute.get(0));
        int minIndex=0;
        for (int i = 1; i < allLinkRoute.size(); i++) {
            int curLinkDistance=calcLinkNodeDistance(allLinkRoute.get(i));
             if (curLinkDistance<minDistance){
                 minDistance=curLinkDistance;
                 minIndex=i;
             }
        }
        System.out.println(startNode.getName()+"到"+endNode.getName()+"最短路径的:");
        calcLinkNodeDistance(allLinkRoute.get(minIndex));
        return minDistance;
    }


    private static int calcLinkNodeDistance(LinkedList<TwoStraightNodeInfo> twoTreeNodes) {
        int distance=0;
        for (int i = 0; i < twoTreeNodes.size() ; i++) {
            distance=distance+ twoTreeNodes.get(i).getDistance();
             System.out.print(twoTreeNodes.get(i).getStartNode().getName()+"--->");
             if (i==twoTreeNodes.size()-1){
                 System.out.print(twoTreeNodes.get(i).getEndNode().getName()+"--->");
             }
        }
        System.out.println("距离为："+distance);
        return distance;
    }

    /**
     *
     * @param lastLinkNodeList   递归时用的参数，表示上一个路径中的一条链
     * @param startNode  开始的节点
     * @param endNode   结束的节点
     * @return   返回所有可达的链条
     */
    public static   ArrayList<LinkedList<TwoStraightNodeInfo>> getRouteBetweenTwoNode(LinkedList<TwoStraightNodeInfo> lastLinkNodeList, TreeNode startNode, TreeNode endNode) {
        ArrayList<LinkedList<TwoStraightNodeInfo>> res = new ArrayList<>();
        for (TwoStraightNodeInfo twoNodeInfo : startNode.getNextNodes()) {
            LinkedList<TwoStraightNodeInfo> lastLinkNodeListClone;
            if (lastLinkNodeList != null) {
                lastLinkNodeListClone = (LinkedList<TwoStraightNodeInfo>) lastLinkNodeList.clone();
            } else {
                lastLinkNodeListClone = new LinkedList<>();
            }

            if (hasLinkNodeContains(lastLinkNodeListClone,twoNodeInfo.getEndNode())) {//遇到环，这条数据作废
            } else if (twoNodeInfo.isEndNode(endNode)) {//遇到终节点
                lastLinkNodeListClone.add(twoNodeInfo);
                //这个才把整个完整的链条，添加到结果链条中
                res.add(lastLinkNodeListClone);
                continue;
            } else {
                lastLinkNodeListClone.add(twoNodeInfo);
                res.addAll(getRouteBetweenTwoNode(lastLinkNodeListClone, twoNodeInfo.getEndNode(), endNode));//继续向下遍历
            }
        }

        return res;
    }

    private static boolean hasLinkNodeContains(LinkedList<TwoStraightNodeInfo> lastLinkNodeListClone, TreeNode endNode) {
        for (TwoStraightNodeInfo twoNodeInfo:
                lastLinkNodeListClone) {
            if (twoNodeInfo.isEndNode(endNode)||
                    twoNodeInfo.isStartNode(endNode)){
                return true;
            }
        }
        return false;
    }


    public static class TreeNode {
        public List<TwoStraightNodeInfo> getNextNodes() {
            return nextNodes;
        }

        public void setNextNodes(List<TwoStraightNodeInfo> nextNodes) {
            this.nextNodes = nextNodes;
        }

        public List<TwoStraightNodeInfo> getBerforNodes() {
            return berforNodes;
        }

        public void setBerforNodes(List<TwoStraightNodeInfo> berforNodes) {
            this.berforNodes = berforNodes;
        }

        public void addOneNextTwoStraightNodeInfo(TwoStraightNodeInfo nodeInfo) {
            nextNodes.add(nodeInfo);
        }

        public void addOneBerforTwoStraightNodeInfo(TwoStraightNodeInfo nodeInfo) {
            berforNodes.add(nodeInfo);
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        List<TwoStraightNodeInfo> nextNodes = new ArrayList<>();//直达的站点数据
        List<TwoStraightNodeInfo> berforNodes = new ArrayList<>();//被直达的站点数据
        String name;

        TreeNode(String name) {
            this.name = name;
        }
    }

    public static class TwoStraightNodeInfo {//2个节点直间的连线关系

        public TreeNode getStartNode() {
            return startNode;
        }

        public void setStartNode(TreeNode startNode) {
            this.startNode = startNode;
        }

        public TreeNode getEndNode() {
            return endNode;
        }

        public void setEndNode(TreeNode endNode) {
            this.endNode = endNode;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        TwoStraightNodeInfo(TreeNode startNode, TreeNode endNode, int distance) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.distance = distance;
            startNode.addOneNextTwoStraightNodeInfo(this);
            endNode.addOneBerforTwoStraightNodeInfo(this);
        }

        public boolean isStartNode(TreeNode node) {
            return startNode == node;
        }

        public boolean isEndNode(TreeNode node) {
            return endNode == node;
        }

        TreeNode startNode;
        TreeNode endNode;
        int distance;//当前的2个节点的连接方向的距离


    }

    public static void testRxjava2() {

    }

}
