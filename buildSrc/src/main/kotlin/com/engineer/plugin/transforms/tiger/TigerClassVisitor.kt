package com.engineer.plugin.transforms.tiger

import com.engineer.plugin.extensions.PhoenixExtension
import com.engineer.plugin.extensions.model.Constants
import org.gradle.api.Project
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * @author rookie
 * @since 01-03-2020
 */
class TigerClassVisitor(project: Project, classVisitor: ClassVisitor) :
    ClassVisitor(Opcodes.ASM6, classVisitor) {

    private var needHook = false
    private lateinit var mClassName: String
    var classList: HashMap<String, ArrayList<String?>>? = null
    var methodList: ArrayList<String?> = ArrayList()
    private var tigerOn = true

    init {
        val phoneix = project.extensions.findByType(PhoenixExtension::class.java)
        val transform = phoneix?.transform
        classList = transform?.tigerClassList
        tigerOn = transform?.tigerOn ?: true
    }

    override fun visit(
        version: Int, access: Int, name: String,
        signature: String?, superName: String?, interfaces: Array<String>?
    ) {
        super.visit(version, access, name, signature, superName, interfaces)
        mClassName = name
        if (tigerOn && classList?.contains(name) == true) {
            println("find $name in $classList, start hook ")
            methodList = classList?.get(name) ?: ArrayList()
            needHook = true
        }
    }

    override fun visitAnnotation(desc: String?, visible: Boolean): AnnotationVisitor {
        if (tigerOn && desc.equals(Constants.class_annotation)) {
            println("find @Tiger , start hook ")
            needHook = true
        }
        return super.visitAnnotation(desc, visible)
    }

    override fun visitMethod(
        access: Int,
        name: String?,
        desc: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {


        var methodVisitor = super.visitMethod(access, name, desc, signature, exceptions)

        if (tigerOn && needHook) {
            methodVisitor =
                TigerMethodVisitor(
                    Opcodes.ASM6,
                    methodVisitor,
                    access,
                    mClassName,
                    name,
                    desc,
                    methodList
                )
        }

        return methodVisitor
    }
}