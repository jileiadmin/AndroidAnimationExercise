package com.engineer.plugin.transforms.tiger

import com.engineer.plugin.transforms.BaseTransform
import org.gradle.api.Project
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import java.io.InputStream
import java.io.OutputStream
import java.util.function.BiConsumer

/**
 * @author rookie
 * @since 01-03-2020
 */
class TigerTransform(val project: Project) : BaseTransform(project) {

    override fun provideFunction(): BiConsumer<InputStream, OutputStream>? {
        return BiConsumer { t, u ->
            val reader = ClassReader(t)
            val writer = ClassWriter(reader, ClassWriter.COMPUTE_MAXS)
            val visitor = TigerClassVisitor(project, writer)
            reader.accept(visitor, ClassReader.EXPAND_FRAMES)
            val code = writer.toByteArray()
            u.write(code)
        }
    }

    override fun getName(): String {
        return "tiger"
    }
}