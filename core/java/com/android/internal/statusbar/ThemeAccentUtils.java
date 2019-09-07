/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.internal.statusbar;

import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import android.os.RemoteException;
import android.util.Log;

public class ThemeAccentUtils {
    public static final String TAG = "ThemeAccentUtils";

    // Vendor overlays to ignore
    public static final String[] BLACKLIST_VENDOR_OVERLAYS = {
        "SysuiDarkTheme",
        "Pixel",
        "DisplayCutoutEmulationCorner",
        "DisplayCutoutEmulationDouble",
        "DisplayCutoutEmulationNarrow",
        "DisplayCutoutEmulationWide",
    };

    private static final String[] DARK_THEMES = {
        "com.android.system.theme.dark", // 0
        "com.android.settings.theme.dark", // 1
        "com.android.systemui.custom.theme.dark", // 2
        "com.android.dialer.theme.dark", //3
        "com.android.contacts.theme.dark", //4
        "com.android.documentsui.theme.dark", //5
        "com.google.android.apps.wellbeing.theme.dark", //6
    };

    private static final String[] BLACK_THEMES = {
        "com.android.system.theme.black", // 0
        "com.android.settings.theme.black", // 1
        "com.android.systemui.theme.black", // 2
        "com.android.dialer.theme.black", //3
        "com.android.contacts.theme.black", //4
        "com.android.documentsui.theme.black", //5
        "com.google.android.apps.wellbeing.theme.black", //6
    };

    private static final String[] EXTENDED_THEMES = {
        "com.android.system.theme.extended", // 0
        "com.android.settings.theme.extended", // 1
        "com.android.systemui.theme.extended", // 2
        "com.android.dialer.theme.extended", //3
        "com.android.contacts.theme.extended", //4
        "com.android.documentsui.theme.extended", //5
        "com.google.android.apps.wellbeing.theme.extended", //6
    };

    private static final String[] CHOCOLATE_THEMES = {
        "com.android.system.theme.chocolate", // 0
        "com.android.settings.theme.chocolate", // 1
        "com.android.systemui.theme.chocolate", // 2
        "com.android.dialer.theme.chocolate", //3
        "com.android.contacts.theme.chocolate", //4
        "com.android.documentsui.theme.chocolate", //5
        "com.google.android.apps.wellbeing.theme.chocolate", //6
    };

    private static final String[] ELEGANT_THEMES = {
        "com.android.system.theme.elegant", // 0
        "com.android.settings.theme.elegant", // 1
        "com.android.systemui.theme.elegant", // 2
        "com.android.dialer.theme.elegant", //4
        "com.android.contacts.theme.elegant", //5
        "com.android.documentsui.theme.elegant", //6
        "com.google.android.apps.wellbeing.theme.elegant", //7
	"com.google.android.apps.gms.theme.elegant", //8
    };

    private static final String[] QS_TILE_THEMES = {
        "default_qstile", // 0
        "com.android.systemui.qstile.squircle", // 1
        "com.android.systemui.qstile.teardrop", // 2
        "com.android.systemui.qstile.deletround", // 3
        "com.android.systemui.qstile.inktober", // 4
        "com.android.systemui.qstile.shishunights", // 5
        "com.android.systemui.qstile.circlegradient", // 6
        "com.android.systemui.qstile.wavey", // 7
        "com.android.systemui.qstile.circledualtone", // 8
        "com.android.systemui.qstile.squaremedo", // 9
        "com.android.systemui.qstile.pokesign", // 10
        "com.android.systemui.qstile.ninja", // 11
        "com.android.systemui.qstile.dottedcircle", // 12
        "com.android.systemui.qstile.shishuink", // 13
        "com.android.systemui.qstile.attemptmountain", // 14
        "com.android.systemui.qstile.cookie", // 15
        "com.android.systemui.qstile.neonlike", // 16
        "com.android.systemui.qstile.oos", // 17
        "com.android.systemui.qstile.triangles", // 18
        "com.android.systemui.qstile.divided", // 19
        "com.android.systemui.qstile.cosmos", // 20
        "com.android.systemui.qstile.badge", //21
        "com.android.systemui.qstile.badgetwo", //22
        "com.android.systemui.qstile.hexagon", // 23
        "com.android.systemui.qstile.star", // 24
        "com.android.systemui.qstile.gear", // 25
        "com.android.systemui.qstile.diamond", // 26
    };

    // QS header themes
    private static final String[] QS_HEADER_THEMES = {
        "com.android.systemui.qsheader.black", // 0
        "com.android.systemui.qsheader.grey", // 1
        "com.android.systemui.qsheader.lightgrey", // 2
        "com.android.systemui.qsheader.accent", // 3
        "com.android.systemui.qsheader.transparent", // 4
    };

    // Switch themes
    private static final String[] SWITCH_THEMES = {
        "com.android.system.switch.stock", // 0
        "com.android.system.switch.md2", // 1
        "com.android.system.switch.oneplus", // 2
        "com.android.system.switch.telegram", // 3
    };

    private static final String STOCK_DARK_THEME = "com.android.systemui.theme.dark";

    // Check for the dark system theme
    public static boolean isUsingDarkTheme(IOverlayManager om, int userId) {
        OverlayInfo themeInfo = null;
        try {
            themeInfo = om.getOverlayInfo(DARK_THEMES[0],
                    userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return themeInfo != null && themeInfo.isEnabled();
    }

    // Check for the black system theme
    public static boolean isUsingBlackTheme(IOverlayManager om, int userId) {
        OverlayInfo themeInfo = null;
        try {
            themeInfo = om.getOverlayInfo(BLACK_THEMES[0],
                    userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return themeInfo != null && themeInfo.isEnabled();
     }

    // Check for the extended system theme
    public static boolean isUsingExtendedTheme(IOverlayManager om, int userId) {
        OverlayInfo themeInfo = null;
        try {
            themeInfo = om.getOverlayInfo(EXTENDED_THEMES[0],
                    userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return themeInfo != null && themeInfo.isEnabled();
     }

    // Check for the chocolate system theme
    public static boolean isUsingChocolateTheme(IOverlayManager om, int userId) {
        OverlayInfo themeInfo = null;
        try {
            themeInfo = om.getOverlayInfo(CHOCOLATE_THEMES[0],
                    userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return themeInfo != null && themeInfo.isEnabled();
     }

    // Check for the elegant system theme
    public static boolean isUsingElegantTheme(IOverlayManager om, int userId) {
        OverlayInfo themeInfo = null;
        try {
            themeInfo = om.getOverlayInfo(ELEGANT_THEMES[0],
                    userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return themeInfo != null && themeInfo.isEnabled();
     }

    public static void setLightDarkTheme(IOverlayManager om, int userId, boolean useDarkTheme) {
        for (String theme : DARK_THEMES) {
                try {
                    om.setEnabled(theme,
                        useDarkTheme, userId);
                    if (useDarkTheme) {
                        unloadStockDarkTheme(om, userId);
                    }
                } catch (RemoteException e) {
                    Log.w(TAG, "Can't change theme", e);
                }
        }
    }

    public static void setLightBlackTheme(IOverlayManager om, int userId, boolean useBlackTheme) {
        for (String theme : BLACK_THEMES) {
                try {
                    om.setEnabled(theme,
                        useBlackTheme, userId);
                  //  unfuckBlackWhiteAccent(om, userId);
                } catch (RemoteException e) {
                    Log.w(TAG, "Can't change theme", e);
                }
        }
    }

    public static void setLightExtendedTheme(IOverlayManager om, int userId, boolean useExtendedTheme) {
        for (String theme : EXTENDED_THEMES) {
                try {
                    om.setEnabled(theme,
                        useExtendedTheme, userId);
                  //  unfuckBlackWhiteAccent(om, userId);
                } catch (RemoteException e) {
                    Log.w(TAG, "Can't change theme", e);
                }
        }
    }

    public static void setLightChocolateTheme(IOverlayManager om, int userId, boolean useChocolateTheme) {
        for (String theme : CHOCOLATE_THEMES) {
                try {
                    om.setEnabled(theme,
                        useChocolateTheme, userId);
                  //  unfuckBlackWhiteAccent(om, userId);
                } catch (RemoteException e) {
                    Log.w(TAG, "Can't change theme", e);
                }
        }
    }

    public static void setLightElegantTheme(IOverlayManager om, int userId, boolean useElegantTheme) {
        for (String theme : ELEGANT_THEMES) {
                try {
                    om.setEnabled(theme,
                        useElegantTheme, userId);
                  //  unfuckBlackWhiteAccent(om, userId);
                } catch (RemoteException e) {
                    Log.w(TAG, "Can't change theme", e);
                }
        }
    }

    // Unloads the stock dark theme
    public static void unloadStockDarkTheme(IOverlayManager om, int userId) {
        OverlayInfo themeInfo = null;
        try {
            themeInfo = om.getOverlayInfo(STOCK_DARK_THEME,
                    userId);
            if (themeInfo != null && themeInfo.isEnabled()) {
                om.setEnabled(STOCK_DARK_THEME,
                        false /*disable*/, userId);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    // Switches qs tile style to user selected.
    public static void updateTileStyle(IOverlayManager om, int userId, int qsTileStyle) {
        if (qsTileStyle == 0) {
            unlockQsTileStyles(om, userId);
        } else {
            try {
                om.setEnabled(QS_TILE_THEMES[qsTileStyle],
                        true, userId);
            } catch (RemoteException e) {
            }
        }
    }


    // Unload all the qs tile styles
    public static void unlockQsTileStyles(IOverlayManager om, int userId) {
        // skip index 0
        for (int i = 1; i < QS_TILE_THEMES.length; i++) {
            String qstiletheme = QS_TILE_THEMES[i];
            try {
                om.setEnabled(qstiletheme,
                        false /*disable*/, userId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    // Check for any QS tile styles overlay
    public static boolean isUsingQsTileStyles(IOverlayManager om, int userId, int qsstyle) {
        OverlayInfo themeInfo = null;
        try {
            themeInfo = om.getOverlayInfo(QS_TILE_THEMES[qsstyle],
                    userId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return themeInfo != null && themeInfo.isEnabled();
    }

    public static void updateSwitchStyle(IOverlayManager om, int userId, int switchStyle) {
        if (switchStyle == 2) {
            stockSwitchStyle(om, userId);
        } else {
            try {
                om.setEnabled(SWITCH_THEMES[switchStyle],
                        true, userId);
            } catch (RemoteException e) {
                Log.w(TAG, "Can't change switch theme", e);
            }
        }
    }

    public static void stockSwitchStyle(IOverlayManager om, int userId) {
        for (int i = 0; i < SWITCH_THEMES.length; i++) {
            String switchtheme = SWITCH_THEMES[i];
            try {
                om.setEnabled(switchtheme,
                        false /*disable*/, userId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    // Switches qs header style to user selected.
    public static void updateQSHeaderStyle(IOverlayManager om, int userId, int qsHeaderStyle) {
        if (qsHeaderStyle == 0) {
            stockQSHeaderStyle(om, userId);
        } else {
            try {
                om.setEnabled(QS_HEADER_THEMES[qsHeaderStyle],
                        true, userId);
            } catch (RemoteException e) {
                Log.w(TAG, "Can't change qs header theme", e);
            }
        }
    }

    // Switches qs header style back to stock.
    public static void stockQSHeaderStyle(IOverlayManager om, int userId) {
        // skip index 0
        for (int i = 1; i < QS_HEADER_THEMES.length; i++) {
            String qsheadertheme = QS_HEADER_THEMES[i];
            try {
                om.setEnabled(qsheadertheme,
                        false /*disable*/, userId);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
