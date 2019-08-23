//package com.allen.idc;
//
//
///**
// * IDC工具类
// */
//public class IdcMachineCodeTest {
//
//    private final static String VALIDATE_IDC_KEY = "VALIDATE_IDC";// 是否强制验证IDC-key
//    private final static Integer DEFAULT_CODE = 1;// 默认机器码
//    private final static Integer MAX_CODE = 32;// 最大机器码(0-31)
//
//    /**
//     * 设置idc机器码，从配置中心取值(key：服务名称_IP)，线上环境强制要求配置值
//     *
//     * @return true设置成功
//     */
//    public static boolean init() {
//        return init(Global.getSingleton().getServiceName());
//    }
//
//    /**
//     * 设置idc机器码，从配置中心取值(key：prefix_IP)，线上环境强制要求配置值
//     *
//     * @param prefix
//     * @return true设置成功
//     */
//    public static boolean init(String prefix) {
//        String key = prefix + "_" + IpUtil.getLocalServerIP();
//        try {
//            // 1. 获取机器码编号
//            Integer code = ApolloUtil.getConfig().getIntProperty(key, null);
//            if (code != null) {
//                // 2. 设置机器码
//                setMachinecode(code);
//                return true;
//            }
//            LOG.error("desc=initMachinecode getIntProperty(", key, ") is null");
//            // 3. 没取到值，需要判断是否需要强制验证IDC（线上环境强制需要）
//            boolean validateIdc = ApolloUtil.getConfig().getBooleanProperty(VALIDATE_IDC_KEY, true);
//            if (!validateIdc) {
//                //4. 不需要验证，取默认机器码
//                setMachinecode(DEFAULT_CODE);
//                return true;
//            }
//        } catch (Exception e) {
//            LOG.error("desc=initMachinecode ex=", E2s.exception2String(e));
//        }
//        return false;
//    }
//
//    /**
//     * 设置机器码
//     *
//     * @param code
//     */
//    private static void setMachinecode(int code) {
//        LOG.info("desc=setMachinecode init code=", code);
//        if (code < 0 || code >= MAX_CODE) {// code in 0-31
//            throw new IllegalArgumentException("worker Id can't be greater than 31 or less than 0");
//        }
//        Idc.getInstance().init(code);
//    }
//
//}
