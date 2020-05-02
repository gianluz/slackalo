package com.gianluz.slackalo

fun slackMessage(config: SlackMessage.() -> Unit): SlackMessage {
    return SlackMessage().apply(config)
}

fun SlackMessage.blocks(blocks: SlackBlocks.() -> Unit) {
    this.blocks.apply(blocks)
}

fun SlackBlocks.markdown(text: String) {
    this.blocks.add(SlackBlock.MarkdownBlock(text))
}

fun SlackBlocks.plainText(text: String) {
    this.blocks.add(SlackBlock.PlainTextBlock(text))
}

fun SlackBlocks.imageMarkdown(text: String, url: String, altText: String) {
    this.blocks.add(SlackBlock.ImageMarkdownBlock(text, url, altText))
}

fun SlackBlocks.imagePlainText(text: String, url: String, altText: String) {
    this.blocks.add(SlackBlock.ImagePlainTextBlock(text, url, altText))
}

fun SlackBlocks.imageBlock(text: String, url: String, altText: String, emoji: Boolean) {
    this.blocks.add(SlackBlock.ImageBlock(text, url, altText, emoji))
}

fun SlackBlocks.fields(fields: SlackBlock.FieldsBlock.() -> Unit) {
    val blocks = SlackBlock.FieldsBlock(mutableListOf())
    fields(blocks)
    this.blocks.add(blocks)
}

fun SlackBlocks.divider() {
    this.blocks.add(SlackBlock.Divider)
}

fun SlackBlock.FieldsBlock.markdown(text: String) {
    this.fields.add(Field.MarkdownTextField(text))
}

fun SlackBlock.FieldsBlock.plainText(text: String, emoji: Boolean) {
    this.fields.add(Field.PlainTextField(text, emoji))
}
