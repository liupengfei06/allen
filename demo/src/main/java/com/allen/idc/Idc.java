
package com.allen.idc;

public class Idc {
    private static IdCreater idc = new IdCreater(0L);

    public Idc() {
    }

    public static Idc getInstance() {
        return Idc.IdcHolder.idc;
    }

    public void init(int id) {
        idc = new IdCreater((long)id);
    }

    public long get() {
        return idc.nextId();
    }

    public long get(int id) {
        return idc.nextId((long)id);
    }

    private static class IdcHolder {
        public static Idc idc = new Idc();

        private IdcHolder() {
        }
    }
}
