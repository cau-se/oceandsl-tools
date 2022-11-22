/**
 */
package org.oceandsl.tools.sar.fxtran;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EN Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.ENType#getN <em>N</em>}</li>
 * </ul>
 *
 * @see org.oceandsl.tools.sar.fxtran.FxtranPackage#getENType()
 * @model extendedMetaData="name='EN_._type' kind='elementOnly'"
 * @generated
 */
public interface ENType extends EObject {
    /**
     * Returns the value of the '<em><b>N</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>N</em>' containment reference.
     * @see #setN(NType)
     * @see org.oceandsl.tools.sar.fxtran.FxtranPackage#getENType_N()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='N' namespace='##targetNamespace'"
     * @generated
     */
    NType getN();

    /**
     * Sets the value of the '{@link org.oceandsl.tools.sar.fxtran.ENType#getN <em>N</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>N</em>' containment reference.
     * @see #getN()
     * @generated
     */
    void setN(NType value);

} // ENType
