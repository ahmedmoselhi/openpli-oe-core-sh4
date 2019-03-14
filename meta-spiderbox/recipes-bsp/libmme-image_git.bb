DESCRIPTION = "MME image library"

require sh4-apps.inc

DEPENDS += " jpeg"

do_install_append () {
	install -d ${D}${includedir}/mmeimage
	install -m 644 ${S}/*.h ${D}${includedir}/mmeimage
}

FILES_${PN} += "${libdir}/libmmeimage.so"
FILES_${PN}-dev += "${includedir}/mmeimage ${includedir}/libmmeimage  ${libdir}/libmmeimage.la"

INSANE_SKIP_${PN} += "dev-so"

