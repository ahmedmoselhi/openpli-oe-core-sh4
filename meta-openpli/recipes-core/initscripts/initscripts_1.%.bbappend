FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append_sh4 = "\
    file://sh4_initscripts.patch \
"
