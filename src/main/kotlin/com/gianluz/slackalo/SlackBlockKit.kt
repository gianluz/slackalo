package com.gianluz.slackalo

class SlackMessage : SlackBlockKitMap {
    val blocks: SlackBlocks = SlackBlocks(mutableListOf())
    override fun asSlackBlockKitMap(): Map<String, Any> = mapOf(BLOCKS to blocks.blocks.map { it.asSlackBlockKitMap() })
}

data class SlackBlocks(val blocks: MutableList<SlackBlock>) {
    operator fun SlackBlock.unaryPlus() = blocks.add(this)
}

sealed class SlackBlock : SlackBlockKitMap {
    data class MarkdownBlock(val text: String) : SlackBlock() {
        override fun asSlackBlockKitMap(): Map<String, Any> = mapOf(
                TYPE to TYPE_SECTION,
                TEXT to mapOf(
                        TYPE to TYPE_MRKDWN,
                        TEXT to text
                )
        )
    }

    data class PlainTextBlock(val text: String) : SlackBlock() {
        override fun asSlackBlockKitMap(): Map<String, Any> = mapOf(
                TYPE to TYPE_SECTION,
                TEXT to mapOf(
                        TYPE to TYPE_PLAIN_TEXT,
                        TEXT to text
                )
        )
    }

    data class ImageMarkdownBlock(val text: String, val url: String, val altText: String) : SlackBlock() {
        override fun asSlackBlockKitMap(): Map<String, Any> = mapOf(
                TYPE to TYPE_SECTION,
                TEXT to mapOf(
                        TYPE to TYPE_MRKDWN,
                        TEXT to text
                ),
                ACCESSORY to mapOf(
                        TYPE to TYPE_IMAGE,
                        IMAGE_URL to url,
                        ALT_TEXT to altText
                )
        )
    }

    data class ImagePlainTextBlock(val text: String, val url: String, val altText: String) : SlackBlock() {
        override fun asSlackBlockKitMap(): Map<String, Any> = mapOf(
                TYPE to TYPE_SECTION,
                TEXT to mapOf(
                        TYPE to TYPE_PLAIN_TEXT,
                        TEXT to text
                ),
                ACCESSORY to mapOf(
                        TYPE to TYPE_IMAGE,
                        IMAGE_URL to url,
                        ALT_TEXT to altText
                )
        )
    }

    data class ImageBlock(val text: String, val url: String, val altText: String, val emoji: Boolean) : SlackBlock() {
        override fun asSlackBlockKitMap(): Map<String, Any> = mapOf(
                TYPE to TYPE_IMAGE,
                TITLE to mapOf(
                        TYPE to TYPE_PLAIN_TEXT,
                        TEXT to text,
                        EMOJI to emoji
                ),
                IMAGE_URL to url,
                ALT_TEXT to altText
        )
    }

    data class FieldsBlock(val fields: MutableList<Field>) : SlackBlock() {
        operator fun Field.unaryPlus() = fields.add(this)

        override fun asSlackBlockKitMap(): Map<String, Any> = mapOf(
                TYPE to TYPE_SECTION,
                FIELDS to fields.map { it.asSlackBlockKitMap() }.toTypedArray()
        )
    }

    object Divider : SlackBlock() {
        override fun asSlackBlockKitMap(): Map<String, Any> = mapOf(TYPE to TYPE_DIVIDER)
    }
}

sealed class Field : SlackBlockKitMap {
    data class PlainTextField(val text: String, val emoji: Boolean) : Field() {
        override fun asSlackBlockKitMap(): Map<String, Any> = mapOf(
                TYPE to TYPE_PLAIN_TEXT,
                TEXT to text,
                EMOJI to emoji
        )
    }

    data class MarkdownTextField(val text: String) : Field() {
        override fun asSlackBlockKitMap(): Map<String, Any> = mapOf(
                TYPE to TYPE_MRKDWN,
                TEXT to text
        )
    }
}
