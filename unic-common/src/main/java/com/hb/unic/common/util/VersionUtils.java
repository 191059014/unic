package com.hb.unic.common.util;

public class VersionUtils {

    /**
     * 初始版本
     */
    private static String InitVersion = "1.0.0";

    public static void main(String[] args) {
        System.out.println(getInitVersion());
        System.out.println(upgradeMajor(InitVersion));
        System.out.println(upgradeMinor(InitVersion));
        System.out.println(upgradePatch(InitVersion));
    }

    /**
     * 获取初始版本
     *
     * @return 版本号
     */
    public static String getInitVersion() {
        return InitVersion;
    }

    /**
     * 更新主要版本号
     *
     * @param version
     *            版本号
     * @return 版本号
     */
    public static String upgradeMajor(String version) {
        String[] versionArr = version.split("\\.");
        int major = Integer.parseInt(versionArr[0]);
        ++major;
        return getFullVersion(major + "", versionArr[1], versionArr[2]);
    }

    /**
     * 更新次要版本号
     *
     * @param version
     *            版本号
     * @return 版本号
     */
    public static String upgradeMinor(String version) {
        String[] versionArr = version.split("\\.");
        int minor = Integer.parseInt(versionArr[1]);
        ++minor;
        return getFullVersion(versionArr[0], minor + "", versionArr[2]);
    }

    /**
     * 更新最小版本号
     *
     * @param version
     *            版本号
     * @return 版本号
     */
    public static String upgradePatch(String version) {
        String[] versionArr = version.split("\\.");
        int patch = Integer.parseInt(versionArr[2]);
        ++patch;
        return getFullVersion(versionArr[0], versionArr[1], patch + "");
    }

    /**
     * 获取完整版本号
     *
     * @param major
     *            主要版本号
     * @param minor
     *            次要版本号
     * @param patch
     *            最小版本号
     * @return 完整版本号
     */
    private static String getFullVersion(String major, String minor, String patch) {
        return String.join(".", major, minor, patch);
    }

}
