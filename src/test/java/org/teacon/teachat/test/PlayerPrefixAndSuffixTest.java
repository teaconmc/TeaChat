package org.teacon.teachat.test;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.contents.LiteralContents;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.teacon.teachat.PlayerPrefixAndSuffix;

public class PlayerPrefixAndSuffixTest {

    @Test
    public void testParsePlainText() {
        final String text = "Test text please ignore";
        final Component component = PlayerPrefixAndSuffix.parseLegacy(text);
        var siblings = component.getSiblings();
        Assertions.assertEquals(1, siblings.size());
        var node1 = siblings.get(0);
        Assertions.assertInstanceOf(LiteralContents.class, node1.getContents());
        Assertions.assertEquals(text, ((LiteralContents)node1.getContents()).text());
    }

    @Test
    public void testParseColoredText() {
        final String text = "&bTest text please ignore";
        final Component component = PlayerPrefixAndSuffix.parseLegacy(text);
        var siblings = component.getSiblings();
        Assertions.assertEquals(1, siblings.size());
        var node1 = siblings.get(0);
        Assertions.assertInstanceOf(LiteralContents.class, node1.getContents());
        Assertions.assertEquals("Test text please ignore", ((LiteralContents)node1.getContents()).text());
        Assertions.assertEquals(TextColor.fromLegacyFormat(ChatFormatting.AQUA), node1.getStyle().getColor());
    }

    @Test
    public void testParseMultiColoredText() {
        final String text = "&bTest text &6please ignore";
        final Component component = PlayerPrefixAndSuffix.parseLegacy(text);
        var siblings = component.getSiblings();
        Assertions.assertEquals(2, siblings.size());
        var node1 = siblings.get(0);
        Assertions.assertInstanceOf(LiteralContents.class, node1.getContents());
        Assertions.assertEquals("Test text ", ((LiteralContents)node1.getContents()).text());
        Assertions.assertEquals(TextColor.fromLegacyFormat(ChatFormatting.AQUA), node1.getStyle().getColor());
        var node2 = siblings.get(1);
        Assertions.assertInstanceOf(LiteralContents.class, node2.getContents());
        Assertions.assertEquals("please ignore", ((LiteralContents)node2.getContents()).text());
        Assertions.assertEquals(TextColor.fromLegacyFormat(ChatFormatting.GOLD), node2.getStyle().getColor());
    }

    @Test
    public void testJustColor() {
        final String text = "&1&2&3&4&5&6";
        final Component component = PlayerPrefixAndSuffix.parseLegacy(text);
        var siblings = component.getSiblings();
        Assertions.assertEquals(0, siblings.size());
    }

    @Test
    public void testParseMultiColoredTextWithBold() {
        final String text = "&bTest text &6&lplease ignore";
        final Component component = PlayerPrefixAndSuffix.parseLegacy(text);
        var siblings = component.getSiblings();
        Assertions.assertEquals(2, siblings.size());
        var node1 = siblings.get(0);
        Assertions.assertInstanceOf(LiteralContents.class, node1.getContents());
        Assertions.assertEquals("Test text ", ((LiteralContents)node1.getContents()).text());
        Assertions.assertEquals(TextColor.fromLegacyFormat(ChatFormatting.AQUA), node1.getStyle().getColor());
        var node2 = siblings.get(1);
        Assertions.assertInstanceOf(LiteralContents.class, node2.getContents());
        Assertions.assertEquals("please ignore", ((LiteralContents)node2.getContents()).text());
        Assertions.assertEquals(TextColor.fromLegacyFormat(ChatFormatting.GOLD), node2.getStyle().getColor());
        Assertions.assertTrue(node2.getStyle().isBold());
    }

    @Test
    public void testRainbow() {
        final String text = "&cR&6a&ei&2n&bb&1o&5w";
        final Component component = PlayerPrefixAndSuffix.parseLegacy(text);
        var siblings = component.getSiblings();
        Assertions.assertEquals(7, siblings.size());
        var node1 = siblings.get(0);
        Assertions.assertInstanceOf(LiteralContents.class, node1.getContents());
        Assertions.assertEquals("R", ((LiteralContents)node1.getContents()).text());
        Assertions.assertEquals(TextColor.fromLegacyFormat(ChatFormatting.RED), node1.getStyle().getColor());
        var node2 = siblings.get(1);
        Assertions.assertInstanceOf(LiteralContents.class, node2.getContents());
        Assertions.assertEquals("a", ((LiteralContents)node2.getContents()).text());
        Assertions.assertEquals(TextColor.fromLegacyFormat(ChatFormatting.GOLD), node2.getStyle().getColor());
        var node3 = siblings.get(2);
        Assertions.assertInstanceOf(LiteralContents.class, node3.getContents());
        Assertions.assertEquals("i", ((LiteralContents)node3.getContents()).text());
        Assertions.assertEquals(TextColor.fromLegacyFormat(ChatFormatting.YELLOW), node3.getStyle().getColor());
        var node4 = siblings.get(3);
        Assertions.assertInstanceOf(LiteralContents.class, node4.getContents());
        Assertions.assertEquals("n", ((LiteralContents)node4.getContents()).text());
        Assertions.assertEquals(TextColor.fromLegacyFormat(ChatFormatting.DARK_GREEN), node4.getStyle().getColor());
        var node5 = siblings.get(4);
        Assertions.assertInstanceOf(LiteralContents.class, node5.getContents());
        Assertions.assertEquals("b", ((LiteralContents)node5.getContents()).text());
        Assertions.assertEquals(TextColor.fromLegacyFormat(ChatFormatting.AQUA), node5.getStyle().getColor());
        var node6 = siblings.get(5);
        Assertions.assertInstanceOf(LiteralContents.class, node6.getContents());
        Assertions.assertEquals("o", ((LiteralContents)node6.getContents()).text());
        Assertions.assertEquals(TextColor.fromLegacyFormat(ChatFormatting.DARK_BLUE), node6.getStyle().getColor());
        var node7 = siblings.get(6);
        Assertions.assertInstanceOf(LiteralContents.class, node7.getContents());
        Assertions.assertEquals("w", ((LiteralContents)node7.getContents()).text());
        Assertions.assertEquals(TextColor.fromLegacyFormat(ChatFormatting.DARK_PURPLE), node7.getStyle().getColor());
    }

    @Test
    public void testResetting() {
        final String text = "&bTest &6text &rplease ignore";
        final Component component = PlayerPrefixAndSuffix.parseLegacy(text);
        var siblings = component.getSiblings();
        Assertions.assertEquals(3, siblings.size());
        var node1 = siblings.get(0);
        Assertions.assertInstanceOf(LiteralContents.class, node1.getContents());
        Assertions.assertEquals("Test ", ((LiteralContents)node1.getContents()).text());
        Assertions.assertEquals(TextColor.fromLegacyFormat(ChatFormatting.AQUA), node1.getStyle().getColor());
        var node2 = siblings.get(1);
        Assertions.assertInstanceOf(LiteralContents.class, node2.getContents());
        Assertions.assertEquals("text ", ((LiteralContents)node2.getContents()).text());
        Assertions.assertEquals(TextColor.fromLegacyFormat(ChatFormatting.GOLD), node2.getStyle().getColor());
        var node3 = siblings.get(2);
        Assertions.assertInstanceOf(LiteralContents.class, node3.getContents());
        Assertions.assertEquals("please ignore", ((LiteralContents)node3.getContents()).text());
        Assertions.assertNull(node3.getStyle().getColor());
    }

}
