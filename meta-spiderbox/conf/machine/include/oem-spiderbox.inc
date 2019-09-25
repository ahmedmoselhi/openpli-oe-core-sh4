# We know about rtc
include conf/machine/include/build-extras.inc
require conf/machine/include/wifi-sh4.inc

BRAND_OEM = "spiderbox"

IMAGEDIR ?= "enigma2"
IMAGE_FSTYPES += "tar.gz"

AUDIOELF = "audio_7100.elf"
VIDEOELF = "video_7109.elf"

SOC_FAMILY = "sti7109"

DVB1FIRMWARE = "dvb-fe-avl2108.fw"
DVB2FIRMWARE = "dvb-fe-stv6306.fw"

KERNEL_MODULE_PACKAGE_SUFFIX = ""

MACHINE_KERNEL_PR = "r1"

TARGET_ARCH = "sh4"
DEFAULTTUNE = "sh4"

GLIBCVERSION = "2.23"
GCCVERSION = "4.9.4"

PREFERRED_PROVIDER_virtual/kernel = "linux-${BRAND_OEM}"
PREFERRED_VERSION_linux-libc-headers = "2.6.32"
OLDEST_KERNEL = "2.6.32"
PREFERRED_VERSION_rt8812au = "4.3.14"
PREFERRED_VERSION_samba = "3.%"
PREFERRED_PROVIDER_lirc = "stlirc"
PREFERRED_PROVIDER_virtual/egl = "libstgles"
PREFERRED_PROVIDER_virtual/libgles1 = "libstgles"
PREFERRED_PROVIDER_virtual/enigma2-mediaservice ?= "enigma2-plugin-systemplugins-servicemp3epl"

SERIAL_CONSOLE = "115200 ttyAS0"

DVBPROVIDER = "kernel"

MACHINE_FEATURES += "ext2 serial switchoff textlcd singlecore legacykernel usbconsole wlan modutils-${BRAND_OEM} spiderbox"
DISTRO_FEATURES_append += "libsigc2"

DVBMEDIASINK_CONFIG = "--with-wma --with-wmv --with-pcm --with-eac3"

KERNEL_IMAGETYPE = "uImage"
#KERNEL_OUTPUT = "${KERNEL_IMAGETYPE}"

# Add uImage in tar image boot directory
IMAGE_CMD_tar_prepend = "\
    cp ${DEPLOY_DIR_IMAGE}/uImage ${IMAGE_ROOTFS}/boot/${KERNEL_FILE}; \
    "

MACHINE_EXTRA_RRECOMMENDS += "\
    enigma2-plugin-systemplugins-serviceapp \
    "

MACHINE_ESSENTIAL_EXTRA_RDEPENDS += "\
    stlirc \
    stlinux24-sh4-stx7109-fdma-firmware \
    firmware-sh4 \
    libmme-host \
    libmme-image \
    stslave-fw \
    ustslave \
    stfbcontrol \
    fp-control \
    stinit \
    ${BRAND_OEM}-dvb-modules \
    "

EXTRA_IMAGEDEPENDS += "\
    libpcre \
    "

MACHINE_EXTRA_RDEPENDS += "\
    libpcre \
    "

include conf/machine/include/autoload-filesystems.inc
require conf/machine/include/tune-sh4.inc
require conf/machine/include/soc-family.inc
