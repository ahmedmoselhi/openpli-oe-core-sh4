DESCRIPTION = "LIRC is a package that allows you to decode and send infra-red signals of many commonly used remote controls."
DESCRIPTION_append_stlirc = " This package contains the lirc daemon, libraries and tools."
DESCRIPTION_append_stlirc-exec = " This package contains a daemon that runs programs on IR signals."
DESCRIPTION_append_stlirc-remotes = " This package contains some config files for remotes."
SECTION = "console/network"
PRIORITY = "optional"
HOMEPAGE = "http://www.lirc.org"
LICENSE = "GPLv2"
DEPENDS = "virtual/kernel"
RDEPENDS_lirc-exec = "stlirc"
RRECOMMENDS_${PN} = "stlirc-exec kernel-module-uinput"
PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "adb_box|arivalink200|ipbox55|ipbox99|ipbox9900|hl101|pace7241|sagemcom88|spark|spark7162|ufs910|vip1_v2|vip2_v1"

RCONFLICTS_${PN} = "lirc"
RCONFLICTS_stlirc-exec = "lirc-exec"
RCONFLICTS_stlirc-remotes = "lirc-remotes"
RREPLACES_${PN} = "lirc"
RREPLACES_stlirc-exec = "lirc-exec"
RREPLACES_stlirc-remotes = "lirc-remotes"
RPROVIDES_${PN} += "lirc"
RPROVIDES_${PN}-exec += "lirc-exec"
RPROVIDES_${PN}-remotes += "lirc-remotes"
PROVIDES += "lirc lirc-exec lirc-remotes"

SRC_URI = "https://sourceforge.net/projects/lirc/files/LIRC/0.9.0/lirc-${PV}.tar.bz2 \
           file://fix-libusb-config.patch \
           file://lircd_${MACHINE}.conf \
           file://lircmd.init \
           file://lircexec.init \
           ${@bb.utils.contains_any("MACHINE", "adb_box arivalink200 ipbox55 ipbox99 ipbox9900 pace7241 sagemcom88 ufs910 vip1_v2 vip2_v1", "file://${PN}_sh4.patch file://lircd_sh4.init", "", d)} \
           ${@bb.utils.contains_any("MACHINE", "hl101 spark spark7162", "file://lircd_spark.init", "", d)} \
           ${@bb.utils.contains_any("MACHINE", "hl101", "file://${PN}_hl101.patch file://lircd.conf.03_00_01 file://lircd.conf.03_00_02 file://lircd.conf.03_00_07", "", d)} \
           ${@bb.utils.contains_any("MACHINE", "spark spark7162", "file://${PN}_spark.patch file://lircd.conf.09_00_07 file://lircd.conf.09_00_08 file://lircd.conf.09_00_0B file://lircd.conf.09_00_1D file://lircd.conf.09_00_0D", "", d)} \
          "

SRC_URI[md5sum] = "b232aef26f23fe33ea8305d276637086"
SRC_URI[sha256sum] = "6323afae6ad498d4369675f77ec3dbb680fe661bea586aa296e67f2e2daba4ff"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

S = "${WORKDIR}/lirc-${PV}"

PARALLEL_MAKE = ""

EXTRA_OECONF += "--with-kerneldir=${STAGING_KERNEL_BUILDDIR} \
                 ${DRIVER} \
                 --without-x \
                 --with-driver=userspace \
                 --enable-sandboxed \
                "

inherit autotools module-base update-rc.d

INITSCRIPT_PACKAGES = "stlirc stlirc-exec"
INITSCRIPT_NAME = "lircd"
INITSCRIPT_PARAMS = "defaults 20"
INITSCRIPT_NAME_stlirc-exec = "lircexec"
INITSCRIPT_PARAMS_stlirc-exec = "defaults 21"

EXTRA_OEMAKE = 'SUBDIRS="daemons tools"'

do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    install ${WORKDIR}/lircexec.init ${D}${sysconfdir}/init.d/lircexec
    install -d ${D}${datadir}/lirc/
    cp -r ${S}/remotes ${D}${datadir}/lirc/
    rm -rf ${D}/dev
    rm -rf  ${D}/bin/pronto2lirc
    if [ "${MACHINE}" = "hl101" ]; then
        install ${WORKDIR}/lircd_spark.init ${D}${sysconfdir}/init.d/lircd
        install -m 0644 ${WORKDIR}/lircd.conf.03_00_* ${D}${sysconfdir}
    elif [ "${MACHINE}" = "spark" -o "${MACHINE}" = "spark7162" ]; then
        install ${WORKDIR}/lircd_spark.init ${D}${sysconfdir}/init.d/lircd
        install -m 0644 ${WORKDIR}/lircd.conf.09_00_* ${D}${sysconfdir}
    else
        install ${WORKDIR}/lircd_sh4.init ${D}${sysconfdir}/init.d/lircd
    fi
    install -m 0644 ${WORKDIR}/lircd_${MACHINE}.conf ${D}${sysconfdir}/lircd.conf
}

PACKAGES =+ "stlirc-exec stlirc-remotes"

FILES_${PN}-dbg += "${bindir}/.debug ${sbindir}/.debug"
FILES_${PN}-dev += "${libdir}/liblirc_client.so"
FILES_${PN} = "${bindir} ${sbindir} ${libdir}/lib*.so.* ${sysconfdir} ${exec_prefix}/var"
FILES_stlirc-exec = "${bindir}/irexec ${sysconfdir}/init.d/lircexec"
FILES_stlirc-remotes = "${datadir}/lirc/remotes"
