SUMMARY = "Firmware files for sh4 fulan"
LICENSE = "CLOSED"
require conf/license/license-close.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "hl101"

SRC_URI = "https://raw.githubusercontent.com/OpenVisionE2/linux-firmwares/master/sh4-firmwares.zip"

SRC_URI[md5sum] = "61b4877cf8170832d82ec876cf6b0a56"
SRC_URI[sha256sum] = "869b07c99b77a54449ed766bdcd6ea219d1860129fe801f2d92d5d515bff69f1"

S = "${WORKDIR}"

PACKAGES = "${PN}"
FILES_${PN} += "${base_libdir}/firmware"

do_install() {
    install -d ${D}${base_libdir}/firmware
    install -m 0644 ${DVB1FIRMWARE} ${D}${base_libdir}/firmware/
    install -m 0644 ${DVB2FIRMWARE} ${D}${base_libdir}/firmware/
}
