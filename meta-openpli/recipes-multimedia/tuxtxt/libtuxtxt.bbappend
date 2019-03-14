FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DEPENDS_append_sh4 += "${BRAND_OEM}-dvb-modules"

SRC_URI_append_sh4 += "\
    file://libtuxtxt-sh4.patch \
"
