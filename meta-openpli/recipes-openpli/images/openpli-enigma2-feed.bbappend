WIFI_DRIVERS_remove_sh4 += "\
	firmware-rtl8712u \
	firmware-carl9170 \
	firmware-htc7010 \
	firmware-htc9271 \
	firmware-rt2870 \
	firmware-rt73 \
	firmware-zd1211 \
	firmware-rtl8723bu \
	\
	kernel-module-ath9k-htc \
	kernel-module-carl9170 \
	kernel-module-r8712u \
	kernel-module-rt2500usb \
	kernel-module-rt2800usb \
	kernel-module-rt2x00usb \
	kernel-module-rt2x00lib \
	kernel-module-rt73usb \
	kernel-module-zd1211rw \
	"

OPTIONAL_WIFI_PACKAGES_remove_sh4 += "\
	kernel-module-8723bu \
	kernel-module-8189es \
	kernel-module-8814au \
	kernel-module-88x2bu \
	kernel-module-mt7610u \
	kernel-module-rtl8822bu \
	wireguard-tools \
	"

OPTIONAL_PACKAGES_remove_sh4 += "\
	gdb \
	nodejs \
	rclone \
	zerotier \
	"
