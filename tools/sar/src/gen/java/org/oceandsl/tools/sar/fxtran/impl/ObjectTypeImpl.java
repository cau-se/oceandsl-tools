/**
 */
package org.oceandsl.tools.sar.fxtran.impl;

import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.oceandsl.tools.sar.fxtran.FileType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.ObjectType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Object Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ObjectTypeImpl#getFile <em>File</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ObjectTypeImpl#getOpenacc <em>Openacc</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ObjectTypeImpl#getOpenmp <em>Openmp</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ObjectTypeImpl#getSourceForm <em>Source Form</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ObjectTypeImpl#getSourceWidth <em>Source Width</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ObjectTypeImpl extends MinimalEObjectImpl.Container implements ObjectType {
    /**
     * The cached value of the '{@link #getFile() <em>File</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFile()
     * @generated
     * @ordered
     */
    protected FileType file;

    /**
     * The default value of the '{@link #getOpenacc() <em>Openacc</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOpenacc()
     * @generated
     * @ordered
     */
    protected static final BigInteger OPENACC_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getOpenacc() <em>Openacc</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOpenacc()
     * @generated
     * @ordered
     */
    protected BigInteger openacc = OPENACC_EDEFAULT;

    /**
     * The default value of the '{@link #getOpenmp() <em>Openmp</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOpenmp()
     * @generated
     * @ordered
     */
    protected static final BigInteger OPENMP_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getOpenmp() <em>Openmp</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOpenmp()
     * @generated
     * @ordered
     */
    protected BigInteger openmp = OPENMP_EDEFAULT;

    /**
     * The default value of the '{@link #getSourceForm() <em>Source Form</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceForm()
     * @generated
     * @ordered
     */
    protected static final String SOURCE_FORM_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSourceForm() <em>Source Form</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceForm()
     * @generated
     * @ordered
     */
    protected String sourceForm = SOURCE_FORM_EDEFAULT;

    /**
     * The default value of the '{@link #getSourceWidth() <em>Source Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceWidth()
     * @generated
     * @ordered
     */
    protected static final BigInteger SOURCE_WIDTH_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSourceWidth() <em>Source Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceWidth()
     * @generated
     * @ordered
     */
    protected BigInteger sourceWidth = SOURCE_WIDTH_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ObjectTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getObjectType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FileType getFile() {
        return file;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFile(FileType newFile, NotificationChain msgs) {
        FileType oldFile = file;
        file = newFile;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.OBJECT_TYPE__FILE, oldFile, newFile);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFile(FileType newFile) {
        if (newFile != file) {
            NotificationChain msgs = null;
            if (file != null)
                msgs = ((InternalEObject)file).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.OBJECT_TYPE__FILE, null, msgs);
            if (newFile != null)
                msgs = ((InternalEObject)newFile).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.OBJECT_TYPE__FILE, null, msgs);
            msgs = basicSetFile(newFile, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.OBJECT_TYPE__FILE, newFile, newFile));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger getOpenacc() {
        return openacc;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOpenacc(BigInteger newOpenacc) {
        BigInteger oldOpenacc = openacc;
        openacc = newOpenacc;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.OBJECT_TYPE__OPENACC, oldOpenacc, openacc));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger getOpenmp() {
        return openmp;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOpenmp(BigInteger newOpenmp) {
        BigInteger oldOpenmp = openmp;
        openmp = newOpenmp;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.OBJECT_TYPE__OPENMP, oldOpenmp, openmp));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getSourceForm() {
        return sourceForm;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSourceForm(String newSourceForm) {
        String oldSourceForm = sourceForm;
        sourceForm = newSourceForm;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.OBJECT_TYPE__SOURCE_FORM, oldSourceForm, sourceForm));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger getSourceWidth() {
        return sourceWidth;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSourceWidth(BigInteger newSourceWidth) {
        BigInteger oldSourceWidth = sourceWidth;
        sourceWidth = newSourceWidth;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.OBJECT_TYPE__SOURCE_WIDTH, oldSourceWidth, sourceWidth));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.OBJECT_TYPE__FILE:
                return basicSetFile(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case FxtranPackage.OBJECT_TYPE__FILE:
                return getFile();
            case FxtranPackage.OBJECT_TYPE__OPENACC:
                return getOpenacc();
            case FxtranPackage.OBJECT_TYPE__OPENMP:
                return getOpenmp();
            case FxtranPackage.OBJECT_TYPE__SOURCE_FORM:
                return getSourceForm();
            case FxtranPackage.OBJECT_TYPE__SOURCE_WIDTH:
                return getSourceWidth();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case FxtranPackage.OBJECT_TYPE__FILE:
                setFile((FileType)newValue);
                return;
            case FxtranPackage.OBJECT_TYPE__OPENACC:
                setOpenacc((BigInteger)newValue);
                return;
            case FxtranPackage.OBJECT_TYPE__OPENMP:
                setOpenmp((BigInteger)newValue);
                return;
            case FxtranPackage.OBJECT_TYPE__SOURCE_FORM:
                setSourceForm((String)newValue);
                return;
            case FxtranPackage.OBJECT_TYPE__SOURCE_WIDTH:
                setSourceWidth((BigInteger)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case FxtranPackage.OBJECT_TYPE__FILE:
                setFile((FileType)null);
                return;
            case FxtranPackage.OBJECT_TYPE__OPENACC:
                setOpenacc(OPENACC_EDEFAULT);
                return;
            case FxtranPackage.OBJECT_TYPE__OPENMP:
                setOpenmp(OPENMP_EDEFAULT);
                return;
            case FxtranPackage.OBJECT_TYPE__SOURCE_FORM:
                setSourceForm(SOURCE_FORM_EDEFAULT);
                return;
            case FxtranPackage.OBJECT_TYPE__SOURCE_WIDTH:
                setSourceWidth(SOURCE_WIDTH_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case FxtranPackage.OBJECT_TYPE__FILE:
                return file != null;
            case FxtranPackage.OBJECT_TYPE__OPENACC:
                return OPENACC_EDEFAULT == null ? openacc != null : !OPENACC_EDEFAULT.equals(openacc);
            case FxtranPackage.OBJECT_TYPE__OPENMP:
                return OPENMP_EDEFAULT == null ? openmp != null : !OPENMP_EDEFAULT.equals(openmp);
            case FxtranPackage.OBJECT_TYPE__SOURCE_FORM:
                return SOURCE_FORM_EDEFAULT == null ? sourceForm != null : !SOURCE_FORM_EDEFAULT.equals(sourceForm);
            case FxtranPackage.OBJECT_TYPE__SOURCE_WIDTH:
                return SOURCE_WIDTH_EDEFAULT == null ? sourceWidth != null : !SOURCE_WIDTH_EDEFAULT.equals(sourceWidth);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (openacc: ");
        result.append(openacc);
        result.append(", openmp: ");
        result.append(openmp);
        result.append(", sourceForm: ");
        result.append(sourceForm);
        result.append(", sourceWidth: ");
        result.append(sourceWidth);
        result.append(')');
        return result.toString();
    }

} //ObjectTypeImpl
