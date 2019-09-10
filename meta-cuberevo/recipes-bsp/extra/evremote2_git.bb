require sh4-apps.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"

COMPATIBLE_MACHINE = "cuberevo|cuberevo_250hd|cuberevo_2000hd|cuberevo_3000hd|cuberevo_9500hd|cuberevo_mini|cuberevo_mini2|cuberevo_mini_fta"
DESCRIPTION = "A tool for SH4 remotes"

SRC_URI_append += "file://evremote2.sh"

LDFLAGS += "-lpthread -lrt"

do_install_append () {
        install -d ${D}/${sysconfdir}/init.d
        install -m 0755 ${WORKDIR}/evremote2.sh ${D}${sysconfdir}/init.d
        install -d ${D}/${sysconfdir}/rcS.d
        ln -sf ../init.d/evremote2.sh ${D}${sysconfdir}/rcS.d/S40evremote2
}
