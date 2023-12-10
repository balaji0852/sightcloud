import { projectStoreID, userStoreID } from "../components/authentication/server-state";



var _state = {
    classMaster: {
        itemMasterID: 0,
        itemName: "empty",
        categoryID: 0,
        subCategoryID: 0,
        itemClassColorID: 0,
        itemPriority: 0,
        isItemCommentable: 0,
        description: "empty",
        createdDate: 0,
        carryForwardMyWork :false,
        userStore: {
            userStoreID: 0
        },
        projectStore: {
            projectStoreID: 0
        }
    },
    projectStoreID: 0,
    //sig-55, balaji:adding openDialog boolean variable,
    //              hourlyDataInstanceFromChild list
    openDialog: false,
    hourlyDataInstanceFromChild: [],
    //balaji: have a requirement to have a redux flag for sidebar state
    valSideBar: false,
    //4/11/2022,balaji: 2940-adding edit and delete
    //          adding the flag setForEdit
    setForEdit: false,
    //8/11/2022- balaji, adding type and pgReRender for the global -gd
    pgReRender: 0,
    type: 0,
    //11/11/2022- balaji, adding view preference, 
    viewPreference: 2,
    //25/11/2023 - Balaji, Adding userStoreID 
    userStoreID: userStoreID
}

export function appReducer(state = _state, action) {


    //balaji: 22/10/22, common let for all, can mutate and return it...
    let stateDup = state;
    console.log(`payload ${JSON.stringify(action.payload)}`)
    switch (action.type) {
        case 'changeClass':

            return {
                classMaster: {
                    itemMasterID: action.payload['classMaster']['itemMasterID'],
                    itemName: action.payload['classMaster']['itemName'],
                    categoryID: action.payload['classMaster']['categoryID'],
                    subCategoryID: action.payload['classMaster']['subCategoryID'],
                    itemClassColorID: action.payload['classMaster']['itemClassColorID'],
                    itemPriority: action.payload['classMaster']['itemPriority'],
                    isItemCommentable: action.payload['classMaster']['isItemCommentable'],
                    carryForwardMyWork: action.payload['classMaster']['carryForwardMyWork'],
                    description: action.payload['classMaster']['description'],
                    createdDate: action.payload['classMaster']['createdDate'],
                    userStore: action.payload['classMaster']['userStore'],
                    projectStore: action.payload['classMaster']['projectStore']
                },
                projectStoreID: action.payload['projectStoreID'],
                openDialog: action.payload['openDialog'],
                hourlyDataInstanceFromChild: action.payload['hourlyDataInstanceFromChild'],
                valSideBar: action.payload['valSideBar'],
                setForEdit: action.payload['setForEdit'],
                pgReRender: action.payload['pgReRender'],
                type: action.payload['type'],
                viewPreference: action.payload['viewPreference'],
                userStoreID: action.payload['userStoreID']
            }
        case 'changeProject':
            stateDup['projectStoreID'] = action.payload['projectStoreID'];
            return stateDup
        //sig-55: adding action, added openDialog,hourlyDataInstanceFromChild,
        //         and sideBar
        //        GD-graphDialog
        case 'changeGraphDialog':
            return {
                classMaster: {
                    itemMasterID: action.payload['classMaster']['itemMasterID'],
                    itemName: action.payload['classMaster']['itemName'],
                    categoryID: action.payload['classMaster']['categoryID'],
                    subCategoryID: action.payload['classMaster']['subCategoryID'],
                    itemClassColorID: action.payload['classMaster']['itemClassColorID'],
                    itemPriority: action.payload['classMaster']['itemPriority'],
                    isItemCommentable: action.payload['classMaster']['isItemCommentable'],
                    carryForwardMyWork: action.payload['classMaster']['carryForwardMyWork'],
                    description: action.payload['classMaster']['description'],
                    createdDate: action.payload['classMaster']['createdDate'],
                    userStore: action.payload['classMaster']['userStore'],
                    projectStore: action.payload['classMaster']['projectStore']
                }, projectStoreID: action.payload['projectStoreID'],
                openDialog: action.payload['openDialog'],
                hourlyDataInstanceFromChild: action.payload['hourlyDataInstanceFromChild'],
                valSideBar: action.payload['valSideBar'],
                setForEdit: action.payload['setForEdit'],
                pgReRender: action.payload['pgReRender'],
                type: action.payload['type'],
                viewPreference: action.payload['viewPreference'],
                userStoreID: action.payload['userStoreID']
            }
        default:
            return state
    }
}
