SUMMARY = "Driver for Ralink rt2870sta"
HOMEPAGE = "http://www.realtek.com/"
SECTION = "kernel/modules"
PACKAGE_ARCH = "${MACHINE_ARCH}"
LICENSE = "GPLv2"
require conf/license/license-gplv2.inc

COMPATIBLE_MACHINE = "adb_box|arivalink200|atemio520|atemio530|atevio7500|cuberevo|cuberevo_250hd|cuberevo_2000hd|cuberevo_3000hd|cuberevo_9500hd|cuberevo_mini|cuberevo_mini2|cuberevo_mini_fta|vip1_v2|pace7241|vip2_v1|fortis_hdbox|hl101|hs7110|hs7119|hs7420|hs7429|hs7810a|hs7819|ipbox55|ipbox99|ipbox9900|sagemcom88|octagon1008|spark|spark7162|tf7700|ufc960|ufs910|ufs912|ufs913|ufs922|vitamin_hd5000"

inherit module

SRCREV = "${AUTOREV}"

FILESEXTRAPATHS_prepend := "${THISDIR}/rt2870sta:"

SRC_URI = " \
	git://github.com/OpenVisionE2/sh4-driver.git;protocol=git \
	file://RT2870STA.dat"

SRC_URI[md5sum] = "0aabffc9071a5ac742bb1e8256110132"
SRC_URI[sha256sum] = "282ad85766ec967596ebf5e84d15ae83cfcda210eeec0c22fb59a983020f6258"

S = "${WORKDIR}/git/wireless/rt2870sta"

EXTRA_OEMAKE = "-e MAKEFLAGS="

CLEANBROKEN = "1"

do_compile() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake KERNEL_PATH=${STAGING_KERNEL_DIR} \
		KERNEL_SRC=${STAGING_KERNEL_DIR} \
		KERNEL_VERSION=${KERNEL_VERSION} \
		-C ${STAGING_KERNEL_DIR} \
		O=${STAGING_KERNEL_BUILDDIR} \
		${@d.getVar('MACHINE',1).upper()}=1 \
		M=${S} V=1
		ARCH=sh
}

do_install() {
	install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless/ralink
	install -m 0644 ${S}/rt2870sta.ko ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless/ralink
	install -d ${D}${sysconfdir}/Wireless/RT2870STA
	install -m 644 ${WORKDIR}/RT2870STA.dat ${D}${sysconfdir}/Wireless/RT2870STA
	
}

FILES_${PN}_append = "${sysconfdir}/Wireless"
