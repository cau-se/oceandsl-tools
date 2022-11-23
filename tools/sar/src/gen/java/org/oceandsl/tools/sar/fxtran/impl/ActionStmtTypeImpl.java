/**
 */
package org.oceandsl.tools.sar.fxtran.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.oceandsl.tools.sar.fxtran.AStmtType;
import org.oceandsl.tools.sar.fxtran.ActionStmtType;
import org.oceandsl.tools.sar.fxtran.AllocateStmtType;
import org.oceandsl.tools.sar.fxtran.CallStmtType;
import org.oceandsl.tools.sar.fxtran.CycleStmtType;
import org.oceandsl.tools.sar.fxtran.DeallocateStmtType;
import org.oceandsl.tools.sar.fxtran.FxtranPackage;
import org.oceandsl.tools.sar.fxtran.PointerAStmtType;
import org.oceandsl.tools.sar.fxtran.WhereStmtType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Action Stmt Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ActionStmtTypeImpl#getReturnStmt <em>Return Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ActionStmtTypeImpl#getWhereStmt <em>Where Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ActionStmtTypeImpl#getAStmt <em>AStmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ActionStmtTypeImpl#getAllocateStmt <em>Allocate Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ActionStmtTypeImpl#getCallStmt <em>Call Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ActionStmtTypeImpl#getDeallocateStmt <em>Deallocate Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ActionStmtTypeImpl#getExitStmt <em>Exit Stmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ActionStmtTypeImpl#getPointerAStmt <em>Pointer AStmt</em>}</li>
 *   <li>{@link org.oceandsl.tools.sar.fxtran.impl.ActionStmtTypeImpl#getCycleStmt <em>Cycle Stmt</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActionStmtTypeImpl extends MinimalEObjectImpl.Container implements ActionStmtType {
    /**
     * The default value of the '{@link #getReturnStmt() <em>Return Stmt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReturnStmt()
     * @generated
     * @ordered
     */
    protected static final String RETURN_STMT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getReturnStmt() <em>Return Stmt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReturnStmt()
     * @generated
     * @ordered
     */
    protected String returnStmt = RETURN_STMT_EDEFAULT;

    /**
     * The cached value of the '{@link #getWhereStmt() <em>Where Stmt</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWhereStmt()
     * @generated
     * @ordered
     */
    protected WhereStmtType whereStmt;

    /**
     * The cached value of the '{@link #getAStmt() <em>AStmt</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAStmt()
     * @generated
     * @ordered
     */
    protected AStmtType aStmt;

    /**
     * The cached value of the '{@link #getAllocateStmt() <em>Allocate Stmt</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAllocateStmt()
     * @generated
     * @ordered
     */
    protected AllocateStmtType allocateStmt;

    /**
     * The cached value of the '{@link #getCallStmt() <em>Call Stmt</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCallStmt()
     * @generated
     * @ordered
     */
    protected CallStmtType callStmt;

    /**
     * The cached value of the '{@link #getDeallocateStmt() <em>Deallocate Stmt</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDeallocateStmt()
     * @generated
     * @ordered
     */
    protected DeallocateStmtType deallocateStmt;

    /**
     * The default value of the '{@link #getExitStmt() <em>Exit Stmt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExitStmt()
     * @generated
     * @ordered
     */
    protected static final String EXIT_STMT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getExitStmt() <em>Exit Stmt</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExitStmt()
     * @generated
     * @ordered
     */
    protected String exitStmt = EXIT_STMT_EDEFAULT;

    /**
     * The cached value of the '{@link #getPointerAStmt() <em>Pointer AStmt</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPointerAStmt()
     * @generated
     * @ordered
     */
    protected PointerAStmtType pointerAStmt;

    /**
     * The cached value of the '{@link #getCycleStmt() <em>Cycle Stmt</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCycleStmt()
     * @generated
     * @ordered
     */
    protected CycleStmtType cycleStmt;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ActionStmtTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FxtranPackage.eINSTANCE.getActionStmtType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getReturnStmt() {
        return returnStmt;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReturnStmt(String newReturnStmt) {
        String oldReturnStmt = returnStmt;
        returnStmt = newReturnStmt;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.ACTION_STMT_TYPE__RETURN_STMT, oldReturnStmt, returnStmt));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public WhereStmtType getWhereStmt() {
        return whereStmt;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetWhereStmt(WhereStmtType newWhereStmt, NotificationChain msgs) {
        WhereStmtType oldWhereStmt = whereStmt;
        whereStmt = newWhereStmt;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.ACTION_STMT_TYPE__WHERE_STMT, oldWhereStmt, newWhereStmt);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWhereStmt(WhereStmtType newWhereStmt) {
        if (newWhereStmt != whereStmt) {
            NotificationChain msgs = null;
            if (whereStmt != null)
                msgs = ((InternalEObject)whereStmt).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ACTION_STMT_TYPE__WHERE_STMT, null, msgs);
            if (newWhereStmt != null)
                msgs = ((InternalEObject)newWhereStmt).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ACTION_STMT_TYPE__WHERE_STMT, null, msgs);
            msgs = basicSetWhereStmt(newWhereStmt, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.ACTION_STMT_TYPE__WHERE_STMT, newWhereStmt, newWhereStmt));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AStmtType getAStmt() {
        return aStmt;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAStmt(AStmtType newAStmt, NotificationChain msgs) {
        AStmtType oldAStmt = aStmt;
        aStmt = newAStmt;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.ACTION_STMT_TYPE__ASTMT, oldAStmt, newAStmt);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAStmt(AStmtType newAStmt) {
        if (newAStmt != aStmt) {
            NotificationChain msgs = null;
            if (aStmt != null)
                msgs = ((InternalEObject)aStmt).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ACTION_STMT_TYPE__ASTMT, null, msgs);
            if (newAStmt != null)
                msgs = ((InternalEObject)newAStmt).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ACTION_STMT_TYPE__ASTMT, null, msgs);
            msgs = basicSetAStmt(newAStmt, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.ACTION_STMT_TYPE__ASTMT, newAStmt, newAStmt));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AllocateStmtType getAllocateStmt() {
        return allocateStmt;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAllocateStmt(AllocateStmtType newAllocateStmt, NotificationChain msgs) {
        AllocateStmtType oldAllocateStmt = allocateStmt;
        allocateStmt = newAllocateStmt;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.ACTION_STMT_TYPE__ALLOCATE_STMT, oldAllocateStmt, newAllocateStmt);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAllocateStmt(AllocateStmtType newAllocateStmt) {
        if (newAllocateStmt != allocateStmt) {
            NotificationChain msgs = null;
            if (allocateStmt != null)
                msgs = ((InternalEObject)allocateStmt).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ACTION_STMT_TYPE__ALLOCATE_STMT, null, msgs);
            if (newAllocateStmt != null)
                msgs = ((InternalEObject)newAllocateStmt).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ACTION_STMT_TYPE__ALLOCATE_STMT, null, msgs);
            msgs = basicSetAllocateStmt(newAllocateStmt, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.ACTION_STMT_TYPE__ALLOCATE_STMT, newAllocateStmt, newAllocateStmt));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CallStmtType getCallStmt() {
        return callStmt;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCallStmt(CallStmtType newCallStmt, NotificationChain msgs) {
        CallStmtType oldCallStmt = callStmt;
        callStmt = newCallStmt;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.ACTION_STMT_TYPE__CALL_STMT, oldCallStmt, newCallStmt);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCallStmt(CallStmtType newCallStmt) {
        if (newCallStmt != callStmt) {
            NotificationChain msgs = null;
            if (callStmt != null)
                msgs = ((InternalEObject)callStmt).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ACTION_STMT_TYPE__CALL_STMT, null, msgs);
            if (newCallStmt != null)
                msgs = ((InternalEObject)newCallStmt).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ACTION_STMT_TYPE__CALL_STMT, null, msgs);
            msgs = basicSetCallStmt(newCallStmt, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.ACTION_STMT_TYPE__CALL_STMT, newCallStmt, newCallStmt));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DeallocateStmtType getDeallocateStmt() {
        return deallocateStmt;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDeallocateStmt(DeallocateStmtType newDeallocateStmt, NotificationChain msgs) {
        DeallocateStmtType oldDeallocateStmt = deallocateStmt;
        deallocateStmt = newDeallocateStmt;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.ACTION_STMT_TYPE__DEALLOCATE_STMT, oldDeallocateStmt, newDeallocateStmt);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDeallocateStmt(DeallocateStmtType newDeallocateStmt) {
        if (newDeallocateStmt != deallocateStmt) {
            NotificationChain msgs = null;
            if (deallocateStmt != null)
                msgs = ((InternalEObject)deallocateStmt).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ACTION_STMT_TYPE__DEALLOCATE_STMT, null, msgs);
            if (newDeallocateStmt != null)
                msgs = ((InternalEObject)newDeallocateStmt).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ACTION_STMT_TYPE__DEALLOCATE_STMT, null, msgs);
            msgs = basicSetDeallocateStmt(newDeallocateStmt, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.ACTION_STMT_TYPE__DEALLOCATE_STMT, newDeallocateStmt, newDeallocateStmt));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getExitStmt() {
        return exitStmt;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExitStmt(String newExitStmt) {
        String oldExitStmt = exitStmt;
        exitStmt = newExitStmt;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.ACTION_STMT_TYPE__EXIT_STMT, oldExitStmt, exitStmt));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PointerAStmtType getPointerAStmt() {
        return pointerAStmt;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPointerAStmt(PointerAStmtType newPointerAStmt, NotificationChain msgs) {
        PointerAStmtType oldPointerAStmt = pointerAStmt;
        pointerAStmt = newPointerAStmt;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.ACTION_STMT_TYPE__POINTER_ASTMT, oldPointerAStmt, newPointerAStmt);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPointerAStmt(PointerAStmtType newPointerAStmt) {
        if (newPointerAStmt != pointerAStmt) {
            NotificationChain msgs = null;
            if (pointerAStmt != null)
                msgs = ((InternalEObject)pointerAStmt).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ACTION_STMT_TYPE__POINTER_ASTMT, null, msgs);
            if (newPointerAStmt != null)
                msgs = ((InternalEObject)newPointerAStmt).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ACTION_STMT_TYPE__POINTER_ASTMT, null, msgs);
            msgs = basicSetPointerAStmt(newPointerAStmt, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.ACTION_STMT_TYPE__POINTER_ASTMT, newPointerAStmt, newPointerAStmt));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CycleStmtType getCycleStmt() {
        return cycleStmt;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCycleStmt(CycleStmtType newCycleStmt, NotificationChain msgs) {
        CycleStmtType oldCycleStmt = cycleStmt;
        cycleStmt = newCycleStmt;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FxtranPackage.ACTION_STMT_TYPE__CYCLE_STMT, oldCycleStmt, newCycleStmt);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCycleStmt(CycleStmtType newCycleStmt) {
        if (newCycleStmt != cycleStmt) {
            NotificationChain msgs = null;
            if (cycleStmt != null)
                msgs = ((InternalEObject)cycleStmt).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ACTION_STMT_TYPE__CYCLE_STMT, null, msgs);
            if (newCycleStmt != null)
                msgs = ((InternalEObject)newCycleStmt).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - FxtranPackage.ACTION_STMT_TYPE__CYCLE_STMT, null, msgs);
            msgs = basicSetCycleStmt(newCycleStmt, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FxtranPackage.ACTION_STMT_TYPE__CYCLE_STMT, newCycleStmt, newCycleStmt));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case FxtranPackage.ACTION_STMT_TYPE__WHERE_STMT:
                return basicSetWhereStmt(null, msgs);
            case FxtranPackage.ACTION_STMT_TYPE__ASTMT:
                return basicSetAStmt(null, msgs);
            case FxtranPackage.ACTION_STMT_TYPE__ALLOCATE_STMT:
                return basicSetAllocateStmt(null, msgs);
            case FxtranPackage.ACTION_STMT_TYPE__CALL_STMT:
                return basicSetCallStmt(null, msgs);
            case FxtranPackage.ACTION_STMT_TYPE__DEALLOCATE_STMT:
                return basicSetDeallocateStmt(null, msgs);
            case FxtranPackage.ACTION_STMT_TYPE__POINTER_ASTMT:
                return basicSetPointerAStmt(null, msgs);
            case FxtranPackage.ACTION_STMT_TYPE__CYCLE_STMT:
                return basicSetCycleStmt(null, msgs);
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
            case FxtranPackage.ACTION_STMT_TYPE__RETURN_STMT:
                return getReturnStmt();
            case FxtranPackage.ACTION_STMT_TYPE__WHERE_STMT:
                return getWhereStmt();
            case FxtranPackage.ACTION_STMT_TYPE__ASTMT:
                return getAStmt();
            case FxtranPackage.ACTION_STMT_TYPE__ALLOCATE_STMT:
                return getAllocateStmt();
            case FxtranPackage.ACTION_STMT_TYPE__CALL_STMT:
                return getCallStmt();
            case FxtranPackage.ACTION_STMT_TYPE__DEALLOCATE_STMT:
                return getDeallocateStmt();
            case FxtranPackage.ACTION_STMT_TYPE__EXIT_STMT:
                return getExitStmt();
            case FxtranPackage.ACTION_STMT_TYPE__POINTER_ASTMT:
                return getPointerAStmt();
            case FxtranPackage.ACTION_STMT_TYPE__CYCLE_STMT:
                return getCycleStmt();
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
            case FxtranPackage.ACTION_STMT_TYPE__RETURN_STMT:
                setReturnStmt((String)newValue);
                return;
            case FxtranPackage.ACTION_STMT_TYPE__WHERE_STMT:
                setWhereStmt((WhereStmtType)newValue);
                return;
            case FxtranPackage.ACTION_STMT_TYPE__ASTMT:
                setAStmt((AStmtType)newValue);
                return;
            case FxtranPackage.ACTION_STMT_TYPE__ALLOCATE_STMT:
                setAllocateStmt((AllocateStmtType)newValue);
                return;
            case FxtranPackage.ACTION_STMT_TYPE__CALL_STMT:
                setCallStmt((CallStmtType)newValue);
                return;
            case FxtranPackage.ACTION_STMT_TYPE__DEALLOCATE_STMT:
                setDeallocateStmt((DeallocateStmtType)newValue);
                return;
            case FxtranPackage.ACTION_STMT_TYPE__EXIT_STMT:
                setExitStmt((String)newValue);
                return;
            case FxtranPackage.ACTION_STMT_TYPE__POINTER_ASTMT:
                setPointerAStmt((PointerAStmtType)newValue);
                return;
            case FxtranPackage.ACTION_STMT_TYPE__CYCLE_STMT:
                setCycleStmt((CycleStmtType)newValue);
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
            case FxtranPackage.ACTION_STMT_TYPE__RETURN_STMT:
                setReturnStmt(RETURN_STMT_EDEFAULT);
                return;
            case FxtranPackage.ACTION_STMT_TYPE__WHERE_STMT:
                setWhereStmt((WhereStmtType)null);
                return;
            case FxtranPackage.ACTION_STMT_TYPE__ASTMT:
                setAStmt((AStmtType)null);
                return;
            case FxtranPackage.ACTION_STMT_TYPE__ALLOCATE_STMT:
                setAllocateStmt((AllocateStmtType)null);
                return;
            case FxtranPackage.ACTION_STMT_TYPE__CALL_STMT:
                setCallStmt((CallStmtType)null);
                return;
            case FxtranPackage.ACTION_STMT_TYPE__DEALLOCATE_STMT:
                setDeallocateStmt((DeallocateStmtType)null);
                return;
            case FxtranPackage.ACTION_STMT_TYPE__EXIT_STMT:
                setExitStmt(EXIT_STMT_EDEFAULT);
                return;
            case FxtranPackage.ACTION_STMT_TYPE__POINTER_ASTMT:
                setPointerAStmt((PointerAStmtType)null);
                return;
            case FxtranPackage.ACTION_STMT_TYPE__CYCLE_STMT:
                setCycleStmt((CycleStmtType)null);
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
            case FxtranPackage.ACTION_STMT_TYPE__RETURN_STMT:
                return RETURN_STMT_EDEFAULT == null ? returnStmt != null : !RETURN_STMT_EDEFAULT.equals(returnStmt);
            case FxtranPackage.ACTION_STMT_TYPE__WHERE_STMT:
                return whereStmt != null;
            case FxtranPackage.ACTION_STMT_TYPE__ASTMT:
                return aStmt != null;
            case FxtranPackage.ACTION_STMT_TYPE__ALLOCATE_STMT:
                return allocateStmt != null;
            case FxtranPackage.ACTION_STMT_TYPE__CALL_STMT:
                return callStmt != null;
            case FxtranPackage.ACTION_STMT_TYPE__DEALLOCATE_STMT:
                return deallocateStmt != null;
            case FxtranPackage.ACTION_STMT_TYPE__EXIT_STMT:
                return EXIT_STMT_EDEFAULT == null ? exitStmt != null : !EXIT_STMT_EDEFAULT.equals(exitStmt);
            case FxtranPackage.ACTION_STMT_TYPE__POINTER_ASTMT:
                return pointerAStmt != null;
            case FxtranPackage.ACTION_STMT_TYPE__CYCLE_STMT:
                return cycleStmt != null;
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
        result.append(" (returnStmt: ");
        result.append(returnStmt);
        result.append(", exitStmt: ");
        result.append(exitStmt);
        result.append(')');
        return result.toString();
    }

} //ActionStmtTypeImpl
