CXXFLAGS_append_sh4 += " -std=c++11 -fPIC -fno-strict-aliasing "

DEPENDS_append_sh4 += "\
	libmme-image \
	libmme-host \
	"

RDEPENDS_${PN}_append_sh4 += "\
	libmme-host \
	"

EXTRA_OECONF_sh4 = "\
	--with-libsdl=no --with-boxtype=${MACHINE} \
	--enable-dependency-tracking \
	ac_cv_prog_c_openmp=-fopenmp \
	${@get_crashaddr(d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "textlcd", "--with-textlcd" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "7segment", "--with-7segment" , "", d)} \
	${@bb.utils.contains("MACHINE_FEATURES", "7seg", "--with-7segment" , "", d)} \
	BUILD_SYS=${BUILD_SYS} \
	HOST_SYS=${HOST_SYS} \
	STAGING_INCDIR=${STAGING_INCDIR} \
	STAGING_LIBDIR=${STAGING_LIBDIR} \
	"

PV_sh4 = "sh4+git${SRCPV}"
PKGV_sh4 = "sh4+git${GITPKGV}"

SRC_URI_sh4 = "git://github.com/sid8796/enigma2-openpli-sh4.git;branch=develop;name=enigma2"
