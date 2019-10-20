package com.allen.hash;

/**
 * 虚拟节点
 */
public class VirtualServerNode {
    private String serverName;//真实节点名称
    private long virtualServerHash;//虚拟节点hash

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public long getVirtualServerHash() {
        return virtualServerHash;
    }

    public void setVirtualServerHash(long virtualServerHash) {
        this.virtualServerHash = virtualServerHash;
    }

}