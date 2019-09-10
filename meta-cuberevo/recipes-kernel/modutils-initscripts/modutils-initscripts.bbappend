COMPATIBLE_MACHINE = "cuberevo|cuberevo_250hd|cuberevo_2000hd|cuberevo_3000hd|cuberevo_9500hd|cuberevo_mini|cuberevo_mini2|cuberevo_mini_fta"

SRC_URI_append += "\
    file://modutils-cuberevo.patch \
"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
