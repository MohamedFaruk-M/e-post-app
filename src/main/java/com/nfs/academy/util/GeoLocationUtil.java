package com.nfs.academy.util;

public class GeoLocationUtil {

    interface Postable {
        Division postalCode();
    }

    public enum Division {
        NORTH(Area.DELHI),
        SOUTH(Area.CHENNAI),
        EAST(Area.CALCUTTA),
        WEST(Area.MUMBAI);

        Division(Area hqLoc) {
        }
    }

    public enum Area implements Postable {
        DELHI {
            @Override
            public Division postalCode() {
                return Division.NORTH;
            }
        },
        CHENNAI {
            @Override
            public Division postalCode() {
                return Division.SOUTH;
            }
        },
        CALCUTTA {
            @Override
            public Division postalCode() {
                return Division.EAST;
            }
        },
        MUMBAI {
            @Override
            public Division postalCode() {
                return Division.WEST;
            }
        }
    }

    public static String findDivision(Area area) {
        return area.postalCode().name();
    }

}
