package com.allen.hash;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;

/**
 * 具有虚拟节点的一致性hash实现（数据结构：list）
 * 一致性hash的真正数据结构是二叉树
 */
public class ConsistentHashWithVirtualNode {
    private List<VirtualServerNode> virtualServers = new ArrayList<>();//存放虚拟节点
    private static final int virtualCount = 8;//每个真实节点虚拟成8个虚拟节点

    /**
     * 计算服务器和存储的键的hash值
     */
    public long hash(String str) {
        CRC32 crc32 = new CRC32();
        crc32.update(str.getBytes());
        return crc32.getValue();
    }

    /**
     * 添加server的虚拟节点到环上
     *
     * @param serverName ip:port
     */
    public void addServer(String serverName) {
        for (int count = 0; count < virtualCount; count++) {
            VirtualServerNode node = new VirtualServerNode();
            node.setServerName(serverName);
            node.setVirtualServerHash(hash(serverName + "-" + count));//虚拟节点的名字：serverName+"-"+count
            virtualServers.add(node);
        }

        virtualServers.sort((node1, node2) -> {
            if (node1.getVirtualServerHash() <= node2.getVirtualServerHash()) {
                return -1;
            }
            return 1;
        });
    }

    /**
     * 从环上删除server节点（需要删除所有的该server节点对应的虚拟节点）
     */
    public void deleteServer(String serverName) {

        /*
         * 在这种删除的时候，会出现java.util.ConcurrentModificationException
         * 这是因为此处的遍历方式为使用ArrayList内部类Itr进行遍历，
         * 在遍历的过程中发生了remove、add等操作，导致modCount发生了变化，
         * 产生并发修改异常，
         * 可以使用下边的那一种方式来进行遍历（遍历方式不是Itr），
         * 再这样的遍历过程中，add和remove都是没有问题的
         */
        /*for(VirtualServerNode node : virtualServers){
            if(node.getServerName().equals(serverName)){
                virtualServers.remove(node);
            }
        }*/
        for (int i = 0; i < virtualServers.size(); i++) {
            VirtualServerNode node = virtualServers.get(i);
            if (node.getServerName().equals(serverName)) {
                virtualServers.remove(node);
            }
        }

    }

    /**
     * 获取一个缓存key应该存放的位置
     *
     * @param cachekey 缓存的key
     * @return 缓存的服务器节点
     */
    public VirtualServerNode getServer(String cachekey) {
        long keyHash = hash(cachekey);

        for (VirtualServerNode node : virtualServers) {
            if (keyHash <= node.getVirtualServerHash()) {
                return node;
            }
        }

        return virtualServers.get(0);//如果node没有合适放置位置，放在第一台服务器上去
    }

    /****************测试*******************/
    public void printServers() {
        for (VirtualServerNode server : virtualServers) {
            System.out.println(server.getServerName() + "-->" + server.getVirtualServerHash());
        }
    }

    public static void main(String[] args) {
        ConsistentHashWithVirtualNode ch = new ConsistentHashWithVirtualNode();
        ch.addServer("127.0.0.1:11211");
        ch.addServer("127.0.0.1:11212");
        ch.addServer("127.0.0.2:11211");
        ch.addServer("127.0.0.2:11212");

        ch.printServers();

        VirtualServerNode node = ch.getServer("hello");
        System.out.println(ch.hash("hello") + "-->" + node.getServerName() + "-->" + node.getVirtualServerHash());

        VirtualServerNode node2 = ch.getServer("name");
        System.out.println(ch.hash("name") + "-->" + node2.getServerName() + "-->" + node2.getVirtualServerHash());

        VirtualServerNode node3 = ch.getServer("a");
        System.out.println(ch.hash("a") + "-->" + node3.getServerName() + "-->" + node3.getVirtualServerHash());

        /*********************删除节点之后**********************/
        ch.deleteServer("127.0.0.1:11212");
        ch.printServers();

        VirtualServerNode node0 = ch.getServer("hello");
        System.out.println(ch.hash("hello") + "-->" + node0.getServerName() + "-->" + node0.getVirtualServerHash());

        VirtualServerNode node02 = ch.getServer("name");
        System.out.println(ch.hash("name") + "-->" + node02.getServerName() + "-->" + node02.getVirtualServerHash());

        VirtualServerNode node03 = ch.getServer("a");
        System.out.println(ch.hash("a") + "-->" + node03.getServerName() + "-->" + node03.getVirtualServerHash());

    }
}