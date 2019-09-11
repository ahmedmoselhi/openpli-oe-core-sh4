FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append += "\
	${@bb.utils.contains("MACHINE_FEATURES", "modutils-cuberevo", "file://modutils-cuberevo.patch", "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "modutils-spiderbox", "file://modutils-spiderbox.patch", "", d)} \
	"
