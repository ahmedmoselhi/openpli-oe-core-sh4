DESCRIPTION = "STM ST-231 Coprocessor firmware"
LICENSE = "CLOSED"
SECTION = "base"
inherit allarch

COMPATIBLE_MACHINE = "hl101"

# fix architecture mismatch QA error
INSANE_SKIP_${PN} = "arch"

SRC_URI = "file://${AUDIOELF} \
	file://${VIDEOELF} \
"

# For audio_7109.elf just use audio_7100.elf

FILES_${PN} += "/boot"

do_install () {
    # Remove stuff from old packages if present
    if [ -e /etc/init.d/stslave.sh ] ; then
        rm /etc/init.d/stslave.sh
    fi
    if [ -e /etc/rcS.d/S03stslave ] ; then
        rm /etc/rcS.d/S03stslave
    fi
    install -d ${D}/boot
    install -m 644 ${WORKDIR}/${AUDIOELF}  ${D}/boot/audio.elf
    install -m 644 ${WORKDIR}/${VIDEOELF}  ${D}/boot/video.elf
}
