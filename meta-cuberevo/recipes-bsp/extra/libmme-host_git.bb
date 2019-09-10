DESCRIPTION = "MME image library"

require sh4-apps.inc

DEPENDS += " cuberevo-dvb-modules"

FILES_${PN} += "${libdir}/libmme_host.so"
FILES_${PN}-dev = "${libdir}/libmme_host.la"

INSANE_SKIP_${PN} += "dev-so"
