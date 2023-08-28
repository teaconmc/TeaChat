package org.teacon.teachat;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerPrefixAndSuffix {

    @SubscribeEvent
    public static void formatName(PlayerEvent.NameFormat event) {
        LuckPerms theApi = LuckPermsProvider.get();
        var player = event.getEntity();
        var lpUser = theApi.getUserManager().getUser(player.getGameProfile().getId());
        if (lpUser == null) {
            return;
        }
        var userMeta = lpUser.getCachedData().getMetaData();
        var prefix = userMeta.getPrefix();
        if (prefix != null) {
            player.getPrefixes().add(parseLegacy(prefix));
        }
        var suffix = userMeta.getSuffix();
        if (suffix != null) {
            player.getSuffixes().add(parseLegacy(suffix));
        }
    }

    public static @NotNull MutableComponent parseLegacy(@NotNull String textWithLegacyFormat) {
        MutableComponent result = MutableComponent.create(ComponentContents.EMPTY);
        Style currentStyle = Style.EMPTY;
        final int maxLen = textWithLegacyFormat.length();
        int cursor = 0;
        int lastNonStyle = 0;
        while (cursor < maxLen) {
            if (textWithLegacyFormat.charAt(cursor) == '&') {
                Style nextStyle = updateStyle(currentStyle, textWithLegacyFormat.charAt(cursor + 1));
                if (nextStyle != null) {
                    String sub = textWithLegacyFormat.substring(lastNonStyle, cursor);
                    if (!sub.isEmpty()) {
                        result = result.append(Component.literal(sub).withStyle(currentStyle));
                    }
                    currentStyle = nextStyle;
                    cursor += 2;
                    lastNonStyle = cursor;
                    continue;
                }
            }
            cursor += 1;
        }
        if (lastNonStyle < cursor) {
            String sub = textWithLegacyFormat.substring(lastNonStyle, cursor);
            result = result.append(Component.literal(sub).withStyle(currentStyle));
        }
        return result;
    }

    public static @Nullable Style updateStyle(@NotNull Style currentStyle, char formatCode) {
        return switch (formatCode) {
            case '0' -> currentStyle.withColor(ChatFormatting.BLACK);
            case '1' -> currentStyle.withColor(ChatFormatting.DARK_BLUE);
            case '2' -> currentStyle.withColor(ChatFormatting.DARK_GREEN);
            case '3' -> currentStyle.withColor(ChatFormatting.DARK_AQUA);
            case '4' -> currentStyle.withColor(ChatFormatting.DARK_RED);
            case '5' -> currentStyle.withColor(ChatFormatting.DARK_PURPLE);
            case '6' -> currentStyle.withColor(ChatFormatting.GOLD);
            case '7' -> currentStyle.withColor(ChatFormatting.GRAY);
            case '8' -> currentStyle.withColor(ChatFormatting.DARK_GRAY);
            case '9' -> currentStyle.withColor(ChatFormatting.BLUE);
            case 'a' -> currentStyle.withColor(ChatFormatting.GREEN);
            case 'b' -> currentStyle.withColor(ChatFormatting.AQUA);
            case 'c' -> currentStyle.withColor(ChatFormatting.RED);
            case 'd' -> currentStyle.withColor(ChatFormatting.LIGHT_PURPLE);
            case 'e' -> currentStyle.withColor(ChatFormatting.YELLOW);
            case 'f' -> currentStyle.withColor(ChatFormatting.WHITE);
            case 'k' -> currentStyle.withObfuscated(true);
            case 'l' -> currentStyle.withBold(true);
            case 'm' -> currentStyle.withStrikethrough(true);
            case 'n' -> currentStyle.withUnderlined(true);
            case 'o' -> currentStyle.withItalic(true);
            case 'r' -> Style.EMPTY;
            default -> null;
        };
    }
}
