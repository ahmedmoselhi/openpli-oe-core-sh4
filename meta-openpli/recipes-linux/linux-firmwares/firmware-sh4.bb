SUMMARY = "Firmware files for sh4"
LICENSE = "CLOSED"

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "adb_box|arivalink200|atevio7500|cuberevo|cuberevo_250hd|cuberevo_2000hd|cuberevo_3000hd|cuberevo_9500hd|cuberevo_mini|cuberevo_mini2|cuberevo_mini_fta|vip1_v2|pace7241|vip2_v1|hl101|sagemcom88|octagon1008|tf7700|ufc960|ufs910|ufs913|ufs922|vitamin_hd5000"

SRC_URI = "https://raw.githubusercontent.com/OpenVisionE2/linux-firmwares/master/sh4-firmwares.zip"

SRC_URI[md5sum] = "61b4877cf8170832d82ec876cf6b0a56"
SRC_URI[sha256sum] = "869b07c99b77a54449ed766bdcd6ea219d1860129fe801f2d92d5d515bff69f1"

S = "${WORKDIR}"

PACKAGES = "${PN}"
FILES_${PN} += "${base_libdir}/firmware"

do_install() {
    install -d ${D}${base_libdir}/firmware
    if [ "${MACHINE}" = "adb_box" ]; then
        install -m 0644 dvb-fe-avl2108.fw ${D}${base_libdir}/firmware/
        install -m 0644 dvb-fe-avl6222.fw ${D}${base_libdir}/firmware/
        install -m 0644 as102_data1_st.hex ${D}${base_libdir}/firmware/
        install -m 0644 as102_data2_st.hex ${D}${base_libdir}/firmware/
    elif [ "${MACHINE}" = "tf7700" ]; then
        install -m 0644 dvb-fe-cx24116.fw ${D}${base_libdir}/firmware/
    elif [ "${MACHINE}" = "ufs910" ]; then
        install -m 0644 dvb-fe-cx21143.fw ${D}${base_libdir}/firmware/dvb-fe-cx24116.fw
    elif [ "${MACHINE}" = "pace7241" -o "${MACHINE}" = "sagemcom88" -o "${MACHINE}" = "ufs913" -o "${MACHINE}" = "vitamin_hd5000" ]; then
        install -m 0644 dvb-fe-avl6222.fw ${D}${base_libdir}/firmware/
    elif [ "${MACHINE}" = "ufs922" ]; then
        install -m 0644 dvb-fe-avl2108.fw ${D}${base_libdir}/firmware/
        install -m 0644 dvb-fe-avl6222.fw ${D}${base_libdir}/firmware/
        install -m 0644 dvb-fe-cx21143.fw ${D}${base_libdir}/firmware/
        install -m 0644 dvb-fe-stv6306.fw ${D}${base_libdir}/firmware/
    else
        install -m 0644 ${DVB1FIRMWARE} ${D}${base_libdir}/firmware/
        install -m 0644 ${DVB2FIRMWARE} ${D}${base_libdir}/firmware/
    fi
}

