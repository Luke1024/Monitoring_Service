(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"],{

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! C:\Users\Luke\Documents\ToDo_fullStackApp\webUI\ToDoApp\src\main.ts */"zUnb");


/***/ }),

/***/ "270t":
/*!*******************************************************!*\
  !*** ./src/app/services/task_services/get.service.ts ***!
  \*******************************************************/
/*! exports provided: GetService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "GetService", function() { return GetService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! rxjs/operators */ "kU1M");
/* harmony import */ var _store_store_actions__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../store/store-actions */ "R5um");
/* harmony import */ var _models_task_list_status__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../models/task-list-status */ "wNDw");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/common/http */ "tk/3");
/* harmony import */ var _services_settings_tools__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../../services.settings.tools */ "snQ+");








class GetService {
    constructor(store, http, serviceSettings) {
        this.store = store;
        this.http = http;
        this.serviceSettings = serviceSettings;
        this.token = "";
        this.appState$ = store.select('appState');
        this.appState$.subscribe(app => this.token = app.token);
    }
    getAllTasks() {
        if (this.serviceSettings.tokenReceived()) {
            this.store.dispatch(Object(_store_store_actions__WEBPACK_IMPORTED_MODULE_2__["changeTaskListStatus"])({ taskListStatus: _models_task_list_status__WEBPACK_IMPORTED_MODULE_3__["TaskListStatus"].ALL }));
            this.http.get(this.serviceSettings.tasksUrl + this.token, { observe: 'response' })
                .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_1__["catchError"])(error => this.serviceSettings.handleHttpError(error)))
                .subscribe(response => this.analyzeGetTasksResponse(response));
        }
    }
    getDoneTasks() {
        if (this.serviceSettings.tokenReceived()) {
            this.store.dispatch(Object(_store_store_actions__WEBPACK_IMPORTED_MODULE_2__["changeTaskListStatus"])({ taskListStatus: _models_task_list_status__WEBPACK_IMPORTED_MODULE_3__["TaskListStatus"].DONE }));
            this.http.get(this.serviceSettings.tasksDoneUrl + this.token, { observe: 'response' })
                .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_1__["catchError"])(error => this.serviceSettings.handleHttpError(error)))
                .subscribe(response => this.analyzeGetTasksResponse(response));
        }
    }
    getTodoTasks() {
        if (this.serviceSettings.tokenReceived()) {
            this.store.dispatch(Object(_store_store_actions__WEBPACK_IMPORTED_MODULE_2__["changeTaskListStatus"])({ taskListStatus: _models_task_list_status__WEBPACK_IMPORTED_MODULE_3__["TaskListStatus"].TODO }));
            this.http.get(this.serviceSettings.tasksTodoUrl + this.token, { observe: 'response' })
                .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_1__["catchError"])(error => this.serviceSettings.handleHttpError(error)))
                .subscribe(response => this.analyzeGetTasksResponse(response));
        }
    }
    analyzeGetTasksResponse(response) {
        if (response != null) {
            var status = response.status;
            if (response.body != null) {
                if (status == 200) {
                    this.store.dispatch(Object(_store_store_actions__WEBPACK_IMPORTED_MODULE_2__["createMultipleCards"])({ cards: this.serviceSettings.convertTasks(response.body) }));
                    this.addMessage("Tasks reloaded", true, 0);
                }
            }
        }
    }
    addMessage(message, status, statusCode) {
        this.serviceSettings.addServerManagementMessage(message, status, statusCode);
    }
}
GetService.ɵfac = function GetService_Factory(t) { return new (t || GetService)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_4__["Store"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_angular_common_http__WEBPACK_IMPORTED_MODULE_5__["HttpClient"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_services_settings_tools__WEBPACK_IMPORTED_MODULE_6__["ServicesSettingsAndTools"])); };
GetService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjectable"]({ token: GetService, factory: GetService.ɵfac, providedIn: 'root' });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](GetService, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"],
        args: [{
                providedIn: 'root'
            }]
    }], function () { return [{ type: _ngrx_store__WEBPACK_IMPORTED_MODULE_4__["Store"] }, { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_5__["HttpClient"] }, { type: _services_settings_tools__WEBPACK_IMPORTED_MODULE_6__["ServicesSettingsAndTools"] }]; }, null); })();


/***/ }),

/***/ "8lfd":
/*!******************************************************!*\
  !*** ./src/app/message-bar/message-bar.component.ts ***!
  \******************************************************/
/*! exports provided: MessageBarComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MessageBarComponent", function() { return MessageBarComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "ofXK");




function MessageBarComponent_ul_2_span_2_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "span");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} if (rf & 2) {
    const serverMessage_r1 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]().$implicit;
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](serverMessage_r1.messageStatusCode);
} }
const _c0 = function (a0, a1) { return { "text-success": a0, "text-danger": a1 }; };
function MessageBarComponent_ul_2_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "ul", 3);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "li", 4);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](2, MessageBarComponent_ul_2_span_2_Template, 2, 1, "span", 5);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](3, "span");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](4, "\u00A0\u00A0");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](5, "span");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](6);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} if (rf & 2) {
    const serverMessage_r1 = ctx.$implicit;
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngClass", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵpureFunction2"](3, _c0, serverMessage_r1.messageStatus, !serverMessage_r1.messageStatus));
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", serverMessage_r1.messageStatusCode != 0);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](serverMessage_r1.message);
} }
class MessageBarComponent {
    constructor(store, element) {
        this.store = store;
        this.element = element;
        this.messages = [];
        this.appState$ = store.select('appState');
        this.appState$.subscribe(app => this.updateMessage(app.serverMessages));
    }
    updateMessage(serverMessages) {
        this.messages = serverMessages;
        this.scrollToBottom();
    }
    scrollToBottom() {
        const scrollPane = this.element.nativeElement.querySelector('#list-to-scroll');
        if (scrollPane) {
            setTimeout(() => scrollPane.scrollTop = scrollPane.scrollHeight);
        }
    }
    ngOnInit() {
    }
}
MessageBarComponent.ɵfac = function MessageBarComponent_Factory(t) { return new (t || MessageBarComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_1__["Store"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_angular_core__WEBPACK_IMPORTED_MODULE_0__["ElementRef"])); };
MessageBarComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: MessageBarComponent, selectors: [["app-message-bar"]], decls: 3, vars: 1, consts: [["id", "list-to-scroll", 1, "card", "m-2", "shadow", 2, "height", "100px", "overflow-y", "auto"], [1, "card"], ["class", "list-group list-group-flush", 4, "ngFor", "ngForOf"], [1, "list-group", "list-group-flush"], [1, "list-group-item", 3, "ngClass"], [4, "ngIf"]], template: function MessageBarComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](2, MessageBarComponent_ul_2_Template, 7, 6, "ul", 2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngForOf", ctx.messages);
    } }, directives: [_angular_common__WEBPACK_IMPORTED_MODULE_2__["NgForOf"], _angular_common__WEBPACK_IMPORTED_MODULE_2__["NgClass"], _angular_common__WEBPACK_IMPORTED_MODULE_2__["NgIf"]], styles: ["#panel[_ngcontent-%COMP%] {\r\n    background-color: rgb(255, 255, 255);\r\n    margin-top: 15px;\r\n    position: relative;\r\n    border-radius: 10px;\r\n    border-color: black;\r\n    border-width: 1px;\r\n    border-style:solid;\r\n    max-height:100px;\r\n    overflow-y:scroll;\r\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIm1lc3NhZ2UtYmFyLmNvbXBvbmVudC5jc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7SUFDSSxvQ0FBb0M7SUFDcEMsZ0JBQWdCO0lBQ2hCLGtCQUFrQjtJQUNsQixtQkFBbUI7SUFDbkIsbUJBQW1CO0lBQ25CLGlCQUFpQjtJQUNqQixrQkFBa0I7SUFDbEIsZ0JBQWdCO0lBQ2hCLGlCQUFpQjtBQUNyQiIsImZpbGUiOiJtZXNzYWdlLWJhci5jb21wb25lbnQuY3NzIiwic291cmNlc0NvbnRlbnQiOlsiI3BhbmVsIHtcclxuICAgIGJhY2tncm91bmQtY29sb3I6IHJnYigyNTUsIDI1NSwgMjU1KTtcclxuICAgIG1hcmdpbi10b3A6IDE1cHg7XHJcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7XHJcbiAgICBib3JkZXItcmFkaXVzOiAxMHB4O1xyXG4gICAgYm9yZGVyLWNvbG9yOiBibGFjaztcclxuICAgIGJvcmRlci13aWR0aDogMXB4O1xyXG4gICAgYm9yZGVyLXN0eWxlOnNvbGlkO1xyXG4gICAgbWF4LWhlaWdodDoxMDBweDtcclxuICAgIG92ZXJmbG93LXk6c2Nyb2xsO1xyXG59Il19 */"] });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](MessageBarComponent, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
        args: [{
                selector: 'app-message-bar',
                templateUrl: './message-bar.component.html',
                styleUrls: ['./message-bar.component.css']
            }]
    }], function () { return [{ type: _ngrx_store__WEBPACK_IMPORTED_MODULE_1__["Store"] }, { type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["ElementRef"] }]; }, null); })();


/***/ }),

/***/ "AytR":
/*!*****************************************!*\
  !*** ./src/environments/environment.ts ***!
  \*****************************************/
/*! exports provided: environment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function() { return environment; });
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
const environment = {
    production: false
};
/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.


/***/ }),

/***/ "GITO":
/*!**********************************************************!*\
  !*** ./src/app/services/task_services/delete.service.ts ***!
  \**********************************************************/
/*! exports provided: DeleteService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DeleteService", function() { return DeleteService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _store_store_actions__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../store/store-actions */ "R5um");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs/operators */ "kU1M");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common/http */ "tk/3");
/* harmony import */ var _services_settings_tools__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../../services.settings.tools */ "snQ+");







class DeleteService {
    constructor(store, http, serviceSettings) {
        this.store = store;
        this.http = http;
        this.serviceSettings = serviceSettings;
        this.token = "";
        this.userLogged = false;
        this.appState$ = store.select('appState');
        this.appState$.subscribe(app => this.setState(app));
    }
    setState(state) {
        this.token = state.token;
        this.userLogged = this.userLogged;
    }
    deleteTask(card) {
        if (this.serviceSettings.tokenReceived()) {
            const id = card.id;
            const url = `${this.serviceSettings.tasksUrl + this.token}/${id}`;
            this.http.delete(url, { observe: 'response' })
                .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_2__["catchError"])(error => this.serviceSettings.handleHttpError(error)))
                .subscribe(response => this.analyzeDeleteResponse(card, response));
        }
    }
    analyzeDeleteResponse(card, response) {
        if (response != null) {
            var status = response.status;
            if (response.body != null) {
                if (status == 202) {
                    this.store.dispatch(Object(_store_store_actions__WEBPACK_IMPORTED_MODULE_1__["deleteCard"])({ card }));
                    this.addMessage(response.body.value, true, response.status);
                }
            }
        }
    }
    addMessage(message, messageStatus, messageStatusCode) {
        this.serviceSettings.addServerManagementMessage(message, messageStatus, messageStatusCode);
    }
}
DeleteService.ɵfac = function DeleteService_Factory(t) { return new (t || DeleteService)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_3__["Store"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpClient"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_services_settings_tools__WEBPACK_IMPORTED_MODULE_5__["ServicesSettingsAndTools"])); };
DeleteService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjectable"]({ token: DeleteService, factory: DeleteService.ɵfac, providedIn: 'root' });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](DeleteService, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"],
        args: [{
                providedIn: 'root'
            }]
    }], function () { return [{ type: _ngrx_store__WEBPACK_IMPORTED_MODULE_3__["Store"] }, { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpClient"] }, { type: _services_settings_tools__WEBPACK_IMPORTED_MODULE_5__["ServicesSettingsAndTools"] }]; }, null); })();


/***/ }),

/***/ "GXPF":
/*!******************************************!*\
  !*** ./src/app/tasks/tasks.component.ts ***!
  \******************************************/
/*! exports provided: TasksComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "TasksComponent", function() { return TasksComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _services_task_services_save_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../services/task_services/save.service */ "YntF");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/common */ "ofXK");
/* harmony import */ var _angular_material_button__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/material/button */ "bTqV");
/* harmony import */ var _angular_material_icon__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/material/icon */ "NFeN");
/* harmony import */ var _card_card_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../card/card.component */ "mJ8H");








function TasksComponent_div_0_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](1, "app-card", 2);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} if (rf & 2) {
    const card_r1 = ctx.$implicit;
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("cardInput", card_r1);
} }
class TasksComponent {
    constructor(store, saveService) {
        this.store = store;
        this.saveService = saveService;
        this.cards = [];
        this.maxId = 0;
        this.appState$ = store.select('appState');
        this.appState$.subscribe(app => this.cards = app.cards);
    }
    ngOnInit() { }
    add() {
        var card = { id: 0, taskName: "", description: "", done: false, message: "", messageShow: false, editMode: true, folded: false };
        this.saveService.saveTask(card);
    }
}
TasksComponent.ɵfac = function TasksComponent_Factory(t) { return new (t || TasksComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_1__["Store"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_services_task_services_save_service__WEBPACK_IMPORTED_MODULE_2__["SaveService"])); };
TasksComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: TasksComponent, selectors: [["app-tasks"]], decls: 4, vars: 1, consts: [[4, "ngFor", "ngForOf"], ["mat-icon-button", "", 3, "click"], [3, "cardInput"]], template: function TasksComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](0, TasksComponent_div_0_Template, 2, 1, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "button", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function TasksComponent_Template_button_click_1_listener() { return ctx.add(); });
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "mat-icon");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](3, "add");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngForOf", ctx.cards);
    } }, directives: [_angular_common__WEBPACK_IMPORTED_MODULE_3__["NgForOf"], _angular_material_button__WEBPACK_IMPORTED_MODULE_4__["MatButton"], _angular_material_icon__WEBPACK_IMPORTED_MODULE_5__["MatIcon"], _card_card_component__WEBPACK_IMPORTED_MODULE_6__["CardComponent"]], styles: ["\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJ0YXNrcy5jb21wb25lbnQuY3NzIn0= */"] });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](TasksComponent, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
        args: [{
                selector: 'app-tasks',
                templateUrl: './tasks.component.html',
                styleUrls: ['./tasks.component.css']
            }]
    }], function () { return [{ type: _ngrx_store__WEBPACK_IMPORTED_MODULE_1__["Store"] }, { type: _services_task_services_save_service__WEBPACK_IMPORTED_MODULE_2__["SaveService"] }]; }, null); })();


/***/ }),

/***/ "Q1VL":
/*!*********************************************************!*\
  !*** ./src/app/services/user_services/login.service.ts ***!
  \*********************************************************/
/*! exports provided: LoginService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoginService", function() { return LoginService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _store_store_actions__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../store/store-actions */ "R5um");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs/operators */ "kU1M");
/* harmony import */ var _models_form_panel_mode__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../models/form-panel-mode */ "Qa/w");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/common/http */ "tk/3");
/* harmony import */ var _services_settings_tools__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../../services.settings.tools */ "snQ+");








class LoginService {
    constructor(store, http, serviceSettings) {
        this.store = store;
        this.http = http;
        this.serviceSettings = serviceSettings;
        this.token = "";
        this.userLogged = false;
        this.appState$ = store.select('appState');
        this.appState$.subscribe(app => this.setState(app));
    }
    setState(state) {
        this.token = state.token;
        this.userLogged = this.userLogged;
    }
    loginUser(userCredentials) {
        if (this.serviceSettings.tokenReceived()) {
            if (!userCredentials.userEmail || !userCredentials.userPassword) {
                this.addMessage('Email and password can\'t be blank.', false, 0);
            }
            else {
                this.switchOffFormPanel();
                this.communicateWithServer(userCredentials);
            }
        }
        else {
            this.serviceSettings.addServerManagementMessage(this.serviceSettings.tokenNotFoundMessage, false, 0);
        }
    }
    switchOffFormPanel() {
        this.store.dispatch(Object(_store_store_actions__WEBPACK_IMPORTED_MODULE_1__["setFormPanelMode"])({ mode: _models_form_panel_mode__WEBPACK_IMPORTED_MODULE_3__["FormPanelMode"].NOT_VISIBLE }));
    }
    communicateWithServer(userCredentials) {
        this.http.post(this.serviceSettings.loginUrl + '/' + this.token, userCredentials, { observe: 'response' })
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_2__["catchError"])(error => this.serviceSettings.handleHttpError(error)))
            .subscribe(response => {
            this.analyzeLoginResponse(response, userCredentials);
        });
    }
    analyzeLoginResponse(response, userCredentials) {
        if (response != null) {
            var status = response.status;
            if (response.body != null) {
                if (status == 202) {
                    var message = response.body.value;
                    this.executeLoginOperations(message, userCredentials);
                }
            }
        }
    }
    executeLoginOperations(message, userCredentials) {
        var topBarMessage = "Logged as " + userCredentials.userEmail;
        this.store.dispatch(Object(_store_store_actions__WEBPACK_IMPORTED_MODULE_1__["setTopBarMessage"])({ message: topBarMessage }));
        this.store.dispatch(Object(_store_store_actions__WEBPACK_IMPORTED_MODULE_1__["setUserLoggedToTrue"])());
        this.addMessage("User logged in.", true, 0);
        this.reloadTasks();
    }
    reloadTasks() {
        this.http.get(this.serviceSettings.tasksUrl + this.token, { observe: 'response' })
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_2__["catchError"])(error => this.serviceSettings.handleHttpError(error)))
            .subscribe(response => this.analyzeGetTasksResponse(response));
    }
    analyzeGetTasksResponse(response) {
        if (response != null) {
            var status = response.status;
            if (response.body != null) {
                if (status == 200) {
                    this.store.dispatch(Object(_store_store_actions__WEBPACK_IMPORTED_MODULE_1__["createMultipleCards"])({ cards: this.serviceSettings.convertTasks(response.body) }));
                    this.addMessage("Tasks reloaded", true, 0);
                }
            }
        }
    }
    addMessage(message, status, statusCode) {
        this.serviceSettings.addServerManagementMessage(message, status, statusCode);
    }
}
LoginService.ɵfac = function LoginService_Factory(t) { return new (t || LoginService)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_4__["Store"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_angular_common_http__WEBPACK_IMPORTED_MODULE_5__["HttpClient"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_services_settings_tools__WEBPACK_IMPORTED_MODULE_6__["ServicesSettingsAndTools"])); };
LoginService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjectable"]({ token: LoginService, factory: LoginService.ɵfac, providedIn: 'root' });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](LoginService, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"],
        args: [{
                providedIn: 'root'
            }]
    }], function () { return [{ type: _ngrx_store__WEBPACK_IMPORTED_MODULE_4__["Store"] }, { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_5__["HttpClient"] }, { type: _services_settings_tools__WEBPACK_IMPORTED_MODULE_6__["ServicesSettingsAndTools"] }]; }, null); })();


/***/ }),

/***/ "Qa/w":
/*!*******************************************!*\
  !*** ./src/app/models/form-panel-mode.ts ***!
  \*******************************************/
/*! exports provided: FormPanelMode */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "FormPanelMode", function() { return FormPanelMode; });
var FormPanelMode;
(function (FormPanelMode) {
    FormPanelMode[FormPanelMode["LOG_IN"] = 0] = "LOG_IN";
    FormPanelMode[FormPanelMode["SIGN_IN"] = 1] = "SIGN_IN";
    FormPanelMode[FormPanelMode["NOT_VISIBLE"] = 2] = "NOT_VISIBLE";
})(FormPanelMode || (FormPanelMode = {}));


/***/ }),

/***/ "R5um":
/*!****************************************!*\
  !*** ./src/app/store/store-actions.ts ***!
  \****************************************/
/*! exports provided: setTopBarMessage, setFormPanelMode, setToken, setUserLoggedToTrue, setUserLoggedToFalse, addServerMessage, changeTaskListStatus, createMultipleCards, createCard, updateCard, deleteCard */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "setTopBarMessage", function() { return setTopBarMessage; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "setFormPanelMode", function() { return setFormPanelMode; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "setToken", function() { return setToken; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "setUserLoggedToTrue", function() { return setUserLoggedToTrue; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "setUserLoggedToFalse", function() { return setUserLoggedToFalse; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "addServerMessage", function() { return addServerMessage; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "changeTaskListStatus", function() { return changeTaskListStatus; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "createMultipleCards", function() { return createMultipleCards; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "createCard", function() { return createCard; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "updateCard", function() { return updateCard; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "deleteCard", function() { return deleteCard; });
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @ngrx/store */ "l7P3");

//TopBar component actions
const setTopBarMessage = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createAction"])("[TopBar Component] Set message.", Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["props"])());
//FormPanel component actions
const setFormPanelMode = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createAction"])("[FormPanel Component] Set mode.", Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["props"])());
//ServerManagement actions
const setToken = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createAction"])("[ServerManagement] Set token.", Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["props"])());
const setUserLoggedToTrue = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createAction"])("[ServerManagement] Set user logged to true.");
const setUserLoggedToFalse = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createAction"])("[ServerManagement] Set user logged to false.");
const addServerMessage = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createAction"])("[ServerManagement] Add message.", Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["props"])());
const changeTaskListStatus = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createAction"])("[Task Component] Change task list status.", Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["props"])());
const createMultipleCards = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createAction"])('[Tasks Component] Create multiple cards', Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["props"])());
const createCard = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createAction"])('[Tasks Component] CreateCard', Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["props"])());
const updateCard = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createAction"])('[Tasks Component] UpdateCard', Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["props"])());
const deleteCard = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createAction"])('[Tasks Component] DeleteCard', Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["props"])());


/***/ }),

/***/ "Sy1n":
/*!**********************************!*\
  !*** ./src/app/app.component.ts ***!
  \**********************************/
/*! exports provided: AppComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppComponent", function() { return AppComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _services_user_services_token_service__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./services/user_services/token.service */ "sxXX");
/* harmony import */ var _top_bar_top_bar_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./top-bar/top-bar.component */ "oDk3");
/* harmony import */ var _form_panel_form_panel_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./form-panel/form-panel.component */ "wo+T");
/* harmony import */ var _card_filter_card_filter_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./card-filter/card-filter.component */ "e+Kj");
/* harmony import */ var _message_bar_message_bar_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./message-bar/message-bar.component */ "8lfd");
/* harmony import */ var _tasks_tasks_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./tasks/tasks.component */ "GXPF");








class AppComponent {
    constructor(tokenService) {
        this.tokenService = tokenService;
        this.title = 'ToDoApp';
        tokenService.getToken();
    }
}
AppComponent.ɵfac = function AppComponent_Factory(t) { return new (t || AppComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_services_user_services_token_service__WEBPACK_IMPORTED_MODULE_1__["TokenService"])); };
AppComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: AppComponent, selectors: [["app-root"]], decls: 8, vars: 0, consts: [["id", "basicContainer"], [1, "card"], [1, "card-body"]], template: function AppComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "div", 2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](3, "app-top-bar");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](4, "app-form-panel");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](5, "app-card-filter");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](6, "app-message-bar");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelement"](7, "app-tasks");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } }, directives: [_top_bar_top_bar_component__WEBPACK_IMPORTED_MODULE_2__["TopBarComponent"], _form_panel_form_panel_component__WEBPACK_IMPORTED_MODULE_3__["FormPanelComponent"], _card_filter_card_filter_component__WEBPACK_IMPORTED_MODULE_4__["CardFilterComponent"], _message_bar_message_bar_component__WEBPACK_IMPORTED_MODULE_5__["MessageBarComponent"], _tasks_tasks_component__WEBPACK_IMPORTED_MODULE_6__["TasksComponent"]], styles: ["#basicContainer[_ngcontent-%COMP%] {\r\n    margin:auto;\r\n    margin-top: 15px;\r\n    min-width: 600px;\r\n    max-width: 1000px;\r\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImFwcC5jb21wb25lbnQuY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0lBQ0ksV0FBVztJQUNYLGdCQUFnQjtJQUNoQixnQkFBZ0I7SUFDaEIsaUJBQWlCO0FBQ3JCIiwiZmlsZSI6ImFwcC5jb21wb25lbnQuY3NzIiwic291cmNlc0NvbnRlbnQiOlsiI2Jhc2ljQ29udGFpbmVyIHtcclxuICAgIG1hcmdpbjphdXRvO1xyXG4gICAgbWFyZ2luLXRvcDogMTVweDtcclxuICAgIG1pbi13aWR0aDogNjAwcHg7XHJcbiAgICBtYXgtd2lkdGg6IDEwMDBweDtcclxufSJdfQ== */"] });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](AppComponent, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
        args: [{
                selector: 'app-root',
                templateUrl: './app.component.html',
                styleUrls: ['./app.component.css']
            }]
    }], function () { return [{ type: _services_user_services_token_service__WEBPACK_IMPORTED_MODULE_1__["TokenService"] }]; }, null); })();


/***/ }),

/***/ "YntF":
/*!********************************************************!*\
  !*** ./src/app/services/task_services/save.service.ts ***!
  \********************************************************/
/*! exports provided: SaveService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SaveService", function() { return SaveService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _store_store_actions__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../../store/store-actions */ "R5um");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs/operators */ "kU1M");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common/http */ "tk/3");
/* harmony import */ var _services_settings_tools__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../../services.settings.tools */ "snQ+");







class SaveService {
    constructor(store, http, serviceSettings) {
        this.store = store;
        this.http = http;
        this.serviceSettings = serviceSettings;
        this.token = "";
        this.userLogged = false;
        this.appState$ = store.select('appState');
        this.appState$.subscribe(app => this.setState(app));
    }
    setState(state) {
        this.token = state.token;
        this.userLogged = this.userLogged;
    }
    //202
    saveTask(card) {
        if (this.serviceSettings.tokenReceived()) {
            this.http.post(this.serviceSettings.tasksUrl + this.token, this.serviceSettings.cardToTaskConverter(card), { observe: 'response' })
                .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_2__["catchError"])(error => this.serviceSettings.handleHttpError(error)))
                .subscribe(response => this.analyzeSaveTaskResponse(card, response));
        }
        else {
            this.addMessage(this.serviceSettings.tokenNotFoundMessage, false, 0);
        }
    }
    analyzeSaveTaskResponse(card, response) {
        if (response != null) {
            var status = response.status;
            if (response.body != null) {
                if (status == 200) {
                    var task = response.body;
                    var receivedCard = this.serviceSettings.taskToCardConverter(task);
                    this.store.dispatch(Object(_store_store_actions__WEBPACK_IMPORTED_MODULE_1__["createCard"])({ card: receivedCard }));
                    if (response.body != null && response.status != null) {
                        this.addMessage(response.body.value, true, response.status);
                    }
                }
            }
        }
    }
    addMessage(message, messageStatus, messageStatusCode) {
        this.serviceSettings.addServerManagementMessage(message, messageStatus, messageStatusCode);
    }
}
SaveService.ɵfac = function SaveService_Factory(t) { return new (t || SaveService)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_3__["Store"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpClient"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_services_settings_tools__WEBPACK_IMPORTED_MODULE_5__["ServicesSettingsAndTools"])); };
SaveService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjectable"]({ token: SaveService, factory: SaveService.ɵfac, providedIn: 'root' });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](SaveService, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"],
        args: [{
                providedIn: 'root'
            }]
    }], function () { return [{ type: _ngrx_store__WEBPACK_IMPORTED_MODULE_3__["Store"] }, { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpClient"] }, { type: _services_settings_tools__WEBPACK_IMPORTED_MODULE_5__["ServicesSettingsAndTools"] }]; }, null); })();


/***/ }),

/***/ "ZAI4":
/*!*******************************!*\
  !*** ./src/app/app.module.ts ***!
  \*******************************/
/*! exports provided: AppModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppModule", function() { return AppModule; });
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/platform-browser */ "jhN1");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app.component */ "Sy1n");
/* harmony import */ var _card_card_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./card/card.component */ "mJ8H");
/* harmony import */ var _tasks_tasks_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./tasks/tasks.component */ "GXPF");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/forms */ "3Pt+");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/common/http */ "tk/3");
/* harmony import */ var _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/platform-browser/animations */ "R1ws");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _store_store_reducer__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! ./store/store.reducer */ "kS4j");
/* harmony import */ var _ngrx_store_devtools__WEBPACK_IMPORTED_MODULE_10__ = __webpack_require__(/*! @ngrx/store-devtools */ "agSv");
/* harmony import */ var _angular_material_button__WEBPACK_IMPORTED_MODULE_11__ = __webpack_require__(/*! @angular/material/button */ "bTqV");
/* harmony import */ var _angular_material_slide_toggle__WEBPACK_IMPORTED_MODULE_12__ = __webpack_require__(/*! @angular/material/slide-toggle */ "1jcm");
/* harmony import */ var _angular_material_icon__WEBPACK_IMPORTED_MODULE_13__ = __webpack_require__(/*! @angular/material/icon */ "NFeN");
/* harmony import */ var _card_filter_card_filter_component__WEBPACK_IMPORTED_MODULE_14__ = __webpack_require__(/*! ./card-filter/card-filter.component */ "e+Kj");
/* harmony import */ var _top_bar_top_bar_component__WEBPACK_IMPORTED_MODULE_15__ = __webpack_require__(/*! ./top-bar/top-bar.component */ "oDk3");
/* harmony import */ var _form_panel_form_panel_component__WEBPACK_IMPORTED_MODULE_16__ = __webpack_require__(/*! ./form-panel/form-panel.component */ "wo+T");
/* harmony import */ var _message_bar_message_bar_component__WEBPACK_IMPORTED_MODULE_17__ = __webpack_require__(/*! ./message-bar/message-bar.component */ "8lfd");
/* harmony import */ var _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_18__ = __webpack_require__(/*! @ng-bootstrap/ng-bootstrap */ "1kSV");






















class AppModule {
}
AppModule.ɵmod = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineNgModule"]({ type: AppModule, bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_2__["AppComponent"]] });
AppModule.ɵinj = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineInjector"]({ factory: function AppModule_Factory(t) { return new (t || AppModule)(); }, providers: [], imports: [[
            _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__["BrowserModule"],
            _angular_forms__WEBPACK_IMPORTED_MODULE_5__["FormsModule"],
            _angular_common_http__WEBPACK_IMPORTED_MODULE_6__["HttpClientModule"],
            _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_7__["NoopAnimationsModule"],
            _angular_material_button__WEBPACK_IMPORTED_MODULE_11__["MatButtonModule"],
            _angular_material_slide_toggle__WEBPACK_IMPORTED_MODULE_12__["MatSlideToggleModule"],
            _angular_material_icon__WEBPACK_IMPORTED_MODULE_13__["MatIconModule"],
            _ngrx_store__WEBPACK_IMPORTED_MODULE_8__["StoreModule"].forRoot({ appState: _store_store_reducer__WEBPACK_IMPORTED_MODULE_9__["appReducer"] }),
            _ngrx_store_devtools__WEBPACK_IMPORTED_MODULE_10__["StoreDevtoolsModule"].instrument(),
            _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_18__["NgbModule"]
        ]] });
(function () { (typeof ngJitMode === "undefined" || ngJitMode) && _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵsetNgModuleScope"](AppModule, { declarations: [_app_component__WEBPACK_IMPORTED_MODULE_2__["AppComponent"],
        _card_card_component__WEBPACK_IMPORTED_MODULE_3__["CardComponent"],
        _tasks_tasks_component__WEBPACK_IMPORTED_MODULE_4__["TasksComponent"],
        _card_filter_card_filter_component__WEBPACK_IMPORTED_MODULE_14__["CardFilterComponent"],
        _top_bar_top_bar_component__WEBPACK_IMPORTED_MODULE_15__["TopBarComponent"],
        _form_panel_form_panel_component__WEBPACK_IMPORTED_MODULE_16__["FormPanelComponent"],
        _message_bar_message_bar_component__WEBPACK_IMPORTED_MODULE_17__["MessageBarComponent"]], imports: [_angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__["BrowserModule"],
        _angular_forms__WEBPACK_IMPORTED_MODULE_5__["FormsModule"],
        _angular_common_http__WEBPACK_IMPORTED_MODULE_6__["HttpClientModule"],
        _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_7__["NoopAnimationsModule"],
        _angular_material_button__WEBPACK_IMPORTED_MODULE_11__["MatButtonModule"],
        _angular_material_slide_toggle__WEBPACK_IMPORTED_MODULE_12__["MatSlideToggleModule"],
        _angular_material_icon__WEBPACK_IMPORTED_MODULE_13__["MatIconModule"], _ngrx_store__WEBPACK_IMPORTED_MODULE_8__["StoreRootModule"], _ngrx_store_devtools__WEBPACK_IMPORTED_MODULE_10__["StoreDevtoolsModule"], _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_18__["NgbModule"]] }); })();
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵsetClassMetadata"](AppModule, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"],
        args: [{
                declarations: [
                    _app_component__WEBPACK_IMPORTED_MODULE_2__["AppComponent"],
                    _card_card_component__WEBPACK_IMPORTED_MODULE_3__["CardComponent"],
                    _tasks_tasks_component__WEBPACK_IMPORTED_MODULE_4__["TasksComponent"],
                    _card_filter_card_filter_component__WEBPACK_IMPORTED_MODULE_14__["CardFilterComponent"],
                    _top_bar_top_bar_component__WEBPACK_IMPORTED_MODULE_15__["TopBarComponent"],
                    _form_panel_form_panel_component__WEBPACK_IMPORTED_MODULE_16__["FormPanelComponent"],
                    _message_bar_message_bar_component__WEBPACK_IMPORTED_MODULE_17__["MessageBarComponent"]
                ],
                imports: [
                    _angular_platform_browser__WEBPACK_IMPORTED_MODULE_0__["BrowserModule"],
                    _angular_forms__WEBPACK_IMPORTED_MODULE_5__["FormsModule"],
                    _angular_common_http__WEBPACK_IMPORTED_MODULE_6__["HttpClientModule"],
                    _angular_platform_browser_animations__WEBPACK_IMPORTED_MODULE_7__["NoopAnimationsModule"],
                    _angular_material_button__WEBPACK_IMPORTED_MODULE_11__["MatButtonModule"],
                    _angular_material_slide_toggle__WEBPACK_IMPORTED_MODULE_12__["MatSlideToggleModule"],
                    _angular_material_icon__WEBPACK_IMPORTED_MODULE_13__["MatIconModule"],
                    _ngrx_store__WEBPACK_IMPORTED_MODULE_8__["StoreModule"].forRoot({ appState: _store_store_reducer__WEBPACK_IMPORTED_MODULE_9__["appReducer"] }),
                    _ngrx_store_devtools__WEBPACK_IMPORTED_MODULE_10__["StoreDevtoolsModule"].instrument(),
                    _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_18__["NgbModule"]
                ],
                providers: [],
                bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_2__["AppComponent"]]
            }]
    }], null, null); })();


/***/ }),

/***/ "b0kU":
/*!****************************************************************!*\
  !*** ./src/app/services/user_services/registration.service.ts ***!
  \****************************************************************/
/*! exports provided: RegistrationService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "RegistrationService", function() { return RegistrationService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! rxjs/operators */ "kU1M");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/common/http */ "tk/3");
/* harmony import */ var _services_settings_tools__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../services.settings.tools */ "snQ+");






class RegistrationService {
    constructor(store, http, serviceSettings) {
        this.store = store;
        this.http = http;
        this.serviceSettings = serviceSettings;
        this.token = "";
        this.userLogged = false;
        this.appState$ = store.select('appState');
        this.appState$.subscribe(app => this.setState(app));
    }
    setState(state) {
        this.token = state.token;
        this.userLogged = this.userLogged;
    }
    registerUser(userCredentials) {
        if (this.serviceSettings.tokenReceived()) {
            if (!userCredentials.userEmail || !userCredentials.userPassword) {
                this.addMessage('Email and password can\'t be blank.', false, 0);
            }
            else {
                this.addMessage("Registering user...", true, 0);
                this.http.post(this.serviceSettings.registerUrl + this.token, userCredentials, { observe: 'response' })
                    .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_1__["catchError"])(error => this.serviceSettings.handleHttpError(error)))
                    .subscribe(response => {
                    this.analyzeRegisterResponse(response);
                });
            }
        }
        else {
            this.addMessage(this.serviceSettings.tokenNotFoundMessage, false, 0);
        }
    }
    analyzeRegisterResponse(response) {
        if (response != null) {
            var status = response.status;
            if (response.body != null) {
                var message = response.body.value;
                if (status == 202) {
                    this.addMessage(message, true, status);
                }
            }
        }
    }
    addMessage(message, status, statusCode) {
        this.serviceSettings.addServerManagementMessage(message, status, statusCode);
    }
}
RegistrationService.ɵfac = function RegistrationService_Factory(t) { return new (t || RegistrationService)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_2__["Store"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_angular_common_http__WEBPACK_IMPORTED_MODULE_3__["HttpClient"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_services_settings_tools__WEBPACK_IMPORTED_MODULE_4__["ServicesSettingsAndTools"])); };
RegistrationService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjectable"]({ token: RegistrationService, factory: RegistrationService.ɵfac, providedIn: 'root' });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](RegistrationService, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"],
        args: [{
                providedIn: 'root'
            }]
    }], function () { return [{ type: _ngrx_store__WEBPACK_IMPORTED_MODULE_2__["Store"] }, { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_3__["HttpClient"] }, { type: _services_settings_tools__WEBPACK_IMPORTED_MODULE_4__["ServicesSettingsAndTools"] }]; }, null); })();


/***/ }),

/***/ "e+Kj":
/*!******************************************************!*\
  !*** ./src/app/card-filter/card-filter.component.ts ***!
  \******************************************************/
/*! exports provided: CardFilterComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CardFilterComponent", function() { return CardFilterComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _models_task_list_status__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../models/task-list-status */ "wNDw");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _services_task_services_get_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../services/task_services/get.service */ "270t");
/* harmony import */ var _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @ng-bootstrap/ng-bootstrap */ "1kSV");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/forms */ "3Pt+");







class CardFilterComponent {
    constructor(store, getService) {
        this.store = store;
        this.getService = getService;
        this.taskListStatus = _models_task_list_status__WEBPACK_IMPORTED_MODULE_1__["TaskListStatus"].ALL;
        this.search = "";
        this.model = {
            all: true,
            todo: false,
            done: false,
        };
        this.appState$ = store.select('appState');
        this.appState$.subscribe(app => this.setStatus(app.taskListStatus));
    }
    ngOnInit() {
    }
    setStatus(taskListStatus) {
        if (taskListStatus == _models_task_list_status__WEBPACK_IMPORTED_MODULE_1__["TaskListStatus"].ALL) {
            this.model = { all: true, todo: false, done: false };
        }
        if (taskListStatus == _models_task_list_status__WEBPACK_IMPORTED_MODULE_1__["TaskListStatus"].TODO) {
            this.model = { all: false, todo: true, done: false };
        }
        if (taskListStatus == _models_task_list_status__WEBPACK_IMPORTED_MODULE_1__["TaskListStatus"].DONE) {
            this.model = { all: false, todo: false, done: true };
        }
    }
    all() {
        this.getService.getAllTasks();
    }
    toDo() {
        this.getService.getTodoTasks();
    }
    done() {
        this.getService.getDoneTasks();
    }
}
CardFilterComponent.ɵfac = function CardFilterComponent_Factory(t) { return new (t || CardFilterComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_2__["Store"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_services_task_services_get_service__WEBPACK_IMPORTED_MODULE_3__["GetService"])); };
CardFilterComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: CardFilterComponent, selectors: [["app-card-filter"]], decls: 12, vars: 3, consts: [[1, "card", "m-2", "shadow"], [1, "card-body"], [1, "btn-group", "btn-group-toggle"], ["ngbButtonLabel", "", 1, "btn-outline-primary"], ["type", "checkbox", "ngbButton", "", 3, "ngModel", "click", "ngModelChange"]], template: function CardFilterComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "div", 2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](3, "label", 3);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](4, "input", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function CardFilterComponent_Template_input_click_4_listener() { return ctx.all(); })("ngModelChange", function CardFilterComponent_Template_input_ngModelChange_4_listener($event) { return ctx.model.all = $event; });
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](5, "All ");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](6, "label", 3);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](7, "input", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function CardFilterComponent_Template_input_click_7_listener() { return ctx.toDo(); })("ngModelChange", function CardFilterComponent_Template_input_ngModelChange_7_listener($event) { return ctx.model.todo = $event; });
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](8, "To do ");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](9, "label", 3);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](10, "input", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function CardFilterComponent_Template_input_click_10_listener() { return ctx.done(); })("ngModelChange", function CardFilterComponent_Template_input_ngModelChange_10_listener($event) { return ctx.model.done = $event; });
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](11, "Done ");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngModel", ctx.model.all);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](3);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngModel", ctx.model.todo);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](3);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngModel", ctx.model.done);
    } }, directives: [_ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_4__["NgbButtonLabel"], _angular_forms__WEBPACK_IMPORTED_MODULE_5__["CheckboxControlValueAccessor"], _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_4__["NgbCheckBox"], _angular_forms__WEBPACK_IMPORTED_MODULE_5__["NgControlStatus"], _angular_forms__WEBPACK_IMPORTED_MODULE_5__["NgModel"]], styles: ["\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJjYXJkLWZpbHRlci5jb21wb25lbnQuY3NzIn0= */"] });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](CardFilterComponent, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
        args: [{
                selector: 'app-card-filter',
                templateUrl: './card-filter.component.html',
                styleUrls: ['./card-filter.component.css']
            }]
    }], function () { return [{ type: _ngrx_store__WEBPACK_IMPORTED_MODULE_2__["Store"] }, { type: _services_task_services_get_service__WEBPACK_IMPORTED_MODULE_3__["GetService"] }]; }, null); })();


/***/ }),

/***/ "kS4j":
/*!****************************************!*\
  !*** ./src/app/store/store.reducer.ts ***!
  \****************************************/
/*! exports provided: initialState, appReducer */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "initialState", function() { return initialState; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "appReducer", function() { return appReducer; });
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _store_actions__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./store-actions */ "R5um");
/* harmony import */ var _models_form_panel_mode__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../models/form-panel-mode */ "Qa/w");
/* harmony import */ var _models_task_list_status__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../models/task-list-status */ "wNDw");




const initialState = {
    topBarMessage: "ToDo App",
    formPanelMode: _models_form_panel_mode__WEBPACK_IMPORTED_MODULE_2__["FormPanelMode"].NOT_VISIBLE,
    token: "",
    userLogged: false,
    serverMessages: [],
    taskListStatus: _models_task_list_status__WEBPACK_IMPORTED_MODULE_3__["TaskListStatus"].ALL,
    cards: []
};
const _appReducer = Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["createReducer"])(initialState, 
//TopBar actions
Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["on"])(_store_actions__WEBPACK_IMPORTED_MODULE_1__["setTopBarMessage"], (state, { message }) => (Object.assign(Object.assign({}, state), { topBarMessage: message }))), Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["on"])(_store_actions__WEBPACK_IMPORTED_MODULE_1__["setFormPanelMode"], (state, { mode }) => (Object.assign(Object.assign({}, state), { formPanelMode: mode }))), Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["on"])(_store_actions__WEBPACK_IMPORTED_MODULE_1__["setToken"], (state, { token }) => (Object.assign(Object.assign({}, state), { token: token }))), Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["on"])(_store_actions__WEBPACK_IMPORTED_MODULE_1__["setUserLoggedToTrue"], state => (Object.assign(Object.assign({}, state), { userLogged: true }))), Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["on"])(_store_actions__WEBPACK_IMPORTED_MODULE_1__["setUserLoggedToFalse"], state => (Object.assign(Object.assign({}, state), { userLogged: false }))), Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["on"])(_store_actions__WEBPACK_IMPORTED_MODULE_1__["addServerMessage"], (state, { message }) => (Object.assign(Object.assign({}, state), { serverMessages: messageAdder(state, message) }))), Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["on"])(_store_actions__WEBPACK_IMPORTED_MODULE_1__["changeTaskListStatus"], (state, { taskListStatus }) => (Object.assign(Object.assign({}, state), { taskListStatus: taskListStatus }))), Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["on"])(_store_actions__WEBPACK_IMPORTED_MODULE_1__["createMultipleCards"], (state, { cards }) => (Object.assign(Object.assign({}, state), { cards: cards }))), Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["on"])(_store_actions__WEBPACK_IMPORTED_MODULE_1__["createCard"], (state, { card }) => (Object.assign(Object.assign({}, state), { cards: cardCreator(state, card) }))), Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["on"])(_store_actions__WEBPACK_IMPORTED_MODULE_1__["updateCard"], (state, { card }) => (Object.assign(Object.assign({}, state), { cards: cardUpdater(state, card) }))), Object(_ngrx_store__WEBPACK_IMPORTED_MODULE_0__["on"])(_store_actions__WEBPACK_IMPORTED_MODULE_1__["deleteCard"], (state, { card }) => (Object.assign(Object.assign({}, state), { cards: state.cards.filter(c => c.id !== card.id) }))));
function appReducer(state, action) {
    return _appReducer(state, action);
}
var messageAdder = function (state, message) {
    var messages = state.serverMessages.slice();
    messages.push(message);
    return messages;
};
var cardCreator = function (state, card) {
    var cards = state.cards.slice();
    cards.push(card);
    return cards;
};
var cardUpdater = function (state, card) {
    var cards = state.cards.slice();
    for (var i = 0; i < cards.length; i++) {
        if (cards[i].id == card.id) {
            cards[i] = card;
        }
    }
    return cards;
};


/***/ }),

/***/ "mJ8H":
/*!****************************************!*\
  !*** ./src/app/card/card.component.ts ***!
  \****************************************/
/*! exports provided: CardComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CardComponent", function() { return CardComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _services_task_services_update_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../services/task_services/update.service */ "peRI");
/* harmony import */ var _services_task_services_delete_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../services/task_services/delete.service */ "GITO");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common */ "ofXK");
/* harmony import */ var _angular_material_button__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/material/button */ "bTqV");
/* harmony import */ var _angular_material_icon__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/material/icon */ "NFeN");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/forms */ "3Pt+");









function CardComponent_button_3_Template(rf, ctx) { if (rf & 1) {
    const _r6 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "button", 5);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function CardComponent_button_3_Template_button_click_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r6); const ctx_r5 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); return ctx_r5.fold(); });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "mat-icon");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](2, "expand_less");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} }
function CardComponent_button_4_Template(rf, ctx) { if (rf & 1) {
    const _r8 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "button", 5);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function CardComponent_button_4_Template_button_click_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r8); const ctx_r7 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); return ctx_r7.unfold(); });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "mat-icon");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](2, "expand_more");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} }
function CardComponent_div_10_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "h2", 8);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r2 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](ctx_r2.card.taskName);
} }
function CardComponent_div_11_div_1_button_5_Template(rf, ctx) { if (rf & 1) {
    const _r13 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "button", 11);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function CardComponent_div_11_div_1_button_5_Template_button_click_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r13); const ctx_r12 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](3); return ctx_r12.edit(); });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1, "Edit");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} }
function CardComponent_div_11_div_1_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "h1", 8);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](3, "h2", 9);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](5, CardComponent_div_11_div_1_button_5_Template, 2, 0, "button", 10);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r9 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](ctx_r9.card.taskName);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](ctx_r9.card.description);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", !ctx_r9.card.editMode);
} }
function CardComponent_div_11_div_2_button_9_Template(rf, ctx) { if (rf & 1) {
    const _r16 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "button", 18);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function CardComponent_div_11_div_2_button_9_Template_button_click_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r16); const ctx_r15 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](3); return ctx_r15.save(); });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1, "Save");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} }
function CardComponent_div_11_div_2_Template(rf, ctx) { if (rf & 1) {
    const _r18 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 12);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "label", 13);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](3, "Task name");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](4, "input", 14);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("ngModelChange", function CardComponent_div_11_div_2_Template_input_ngModelChange_4_listener($event) { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r18); const ctx_r17 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2); return ctx_r17.card.taskName = $event; });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](5, "div", 12);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](6, "label", 15);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](7, "Task description");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](8, "input", 16);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("ngModelChange", function CardComponent_div_11_div_2_Template_input_ngModelChange_8_listener($event) { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r18); const ctx_r19 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2); return ctx_r19.card.description = $event; });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](9, CardComponent_div_11_div_2_button_9_Template, 2, 0, "button", 17);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r10 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngModel", ctx_r10.card.taskName);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](4);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngModel", ctx_r10.card.description);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx_r10.card.editMode);
} }
function CardComponent_div_11_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](1, CardComponent_div_11_div_1_Template, 6, 3, "div", 6);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](2, CardComponent_div_11_div_2_Template, 10, 3, "div", 6);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r3 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", !ctx_r3.card.editMode);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx_r3.card.editMode);
} }
function CardComponent_h2_12_Template(rf, ctx) { if (rf & 1) {
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "h2", 19);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r4 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](ctx_r4.card.message);
} }
class CardComponent {
    constructor(store, updateService, deleteService) {
        this.store = store;
        this.updateService = updateService;
        this.deleteService = deleteService;
    }
    ngOnInit() {
        this.card = Object.assign({}, this.cardInput);
    }
    save() {
        if (this.saveAllowed()) {
            this.card.messageShow = false;
            this.card.message = "";
            this.card.editMode = false;
            this.updateStore();
        }
        else {
            this.card.messageShow = true;
            this.card.message = "Task name can't be blank.";
            this.updateStore();
        }
    }
    edit() {
        this.card.editMode = true;
        this.updateStore();
    }
    fold() {
        this.card.folded = true;
        this.updateStore();
    }
    unfold() {
        this.card.folded = false;
        this.updateStore();
    }
    update() {
        if (this.saveAllowed()) {
            this.card.messageShow = false;
            this.card.message = "";
            this.updateStore();
        }
        else {
            this.card.messageShow = true;
            this.card.message = "Task name can't be blank.";
            this.updateStore();
        }
    }
    saveAllowed() {
        if (this.card.taskName.length == 0) {
            return false;
        }
        else {
            return true;
        }
    }
    updateStore() {
        this.updateService.updateTask(this.card);
    }
    delete() {
        this.deleteService.deleteTask(this.cardInput);
    }
    updater() {
        this.update();
    }
}
CardComponent.ɵfac = function CardComponent_Factory(t) { return new (t || CardComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_1__["Store"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_services_task_services_update_service__WEBPACK_IMPORTED_MODULE_2__["UpdateService"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_services_task_services_delete_service__WEBPACK_IMPORTED_MODULE_3__["DeleteService"])); };
CardComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: CardComponent, selectors: [["app-card"]], inputs: { cardInput: "cardInput" }, decls: 13, vars: 6, consts: [[1, "card", "m-2", "shadow"], [1, "card-body"], [2, "float", "right"], ["mat-icon-button", "", 3, "click", 4, "ngIf"], ["type", "checkbox", 3, "checked", "change"], ["mat-icon-button", "", 3, "click"], [4, "ngIf"], ["class", "text-danger", 4, "ngIf"], [1, "card-title"], [1, "card-text"], ["class", "btn btn-primary", 3, "click", 4, "ngIf"], [1, "btn", "btn-primary", 3, "click"], [1, "mb-3"], ["for", "taskname", 1, "form-label"], ["id", "taskname", "aria-describedby", "emailHelp", 1, "form-control", 3, "ngModel", "ngModelChange"], ["for", "taskdescription", 1, "form-label"], ["id", "taskdescription", 1, "form-control", 3, "ngModel", "ngModelChange"], ["class", "btn btn-primary mb-3", 3, "click", 4, "ngIf"], [1, "btn", "btn-primary", "mb-3", 3, "click"], [1, "text-danger"]], template: function CardComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "div", 2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](3, CardComponent_button_3_Template, 3, 0, "button", 3);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](4, CardComponent_button_4_Template, 3, 0, "button", 3);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](5, "input", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("change", function CardComponent_Template_input_change_5_listener() { ctx.card.done = !ctx.card.done; return ctx.updater(); });
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](6, " Done ");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](7, "button", 5);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function CardComponent_Template_button_click_7_listener() { return ctx.delete(); });
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](8, "mat-icon");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](9, "close");
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](10, CardComponent_div_10_Template, 3, 1, "div", 6);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](11, CardComponent_div_11_Template, 3, 2, "div", 6);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](12, CardComponent_h2_12_Template, 2, 1, "h2", 7);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](3);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", !ctx.card.folded);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx.card.folded);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("checked", ctx.card.done);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](5);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx.card.folded);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", !ctx.card.folded);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx.card.messageShow);
    } }, directives: [_angular_common__WEBPACK_IMPORTED_MODULE_4__["NgIf"], _angular_material_button__WEBPACK_IMPORTED_MODULE_5__["MatButton"], _angular_material_icon__WEBPACK_IMPORTED_MODULE_6__["MatIcon"], _angular_forms__WEBPACK_IMPORTED_MODULE_7__["DefaultValueAccessor"], _angular_forms__WEBPACK_IMPORTED_MODULE_7__["NgControlStatus"], _angular_forms__WEBPACK_IMPORTED_MODULE_7__["NgModel"]], styles: ["#card[_ngcontent-%COMP%] {\r\n    background-color: rgb(255, 255, 255);\r\n    margin-top: 15px;\r\n    position: relative;\r\n    border-radius: 10px;\r\n    border-color: black;\r\n    border-width: 1px;\r\n    border-style:solid;\r\n    height:110px;\r\n}\r\n\r\n#name[_ngcontent-%COMP%] {\r\n    position:absolute;\r\n    top:10px;\r\n    left:10px;\r\n    width:70%;\r\n}\r\n\r\n#description[_ngcontent-%COMP%] {\r\n    position:absolute;\r\n    top:40px;\r\n    left:10px;\r\n    width:95%;\r\n    height:50px;\r\n    resize: none;\r\n}\r\n\r\n#saveEdit[_ngcontent-%COMP%] {\r\n    position:absolute;\r\n    top:10px;\r\n    right:130px;\r\n}\r\n\r\n#deleteButton[_ngcontent-%COMP%] {\r\n    position:absolute;\r\n    right:60px;\r\n}\r\n\r\n#doneButton[_ngcontent-%COMP%] {\r\n    position:absolute;\r\n    top:10px;\r\n    right:10px;\r\n}\r\n\r\n#foldExpandArrow[_ngcontent-%COMP%] {\r\n    position:absolute;\r\n    right:90px;\r\n}\r\n\r\n#cardMessage[_ngcontent-%COMP%] {\r\n    background-color: rgb(255, 187, 187);\r\n    margin:auto;\r\n    margin-top: 5px;\r\n    min-width: 400px;\r\n    max-width: 800px;\r\n    position: relative;\r\n    border-radius: 10px;\r\n    height:30px;\r\n    border-color: rgb(255, 187, 187);\r\n    border-width: 3px;\r\n    border-style:solid;\r\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNhcmQuY29tcG9uZW50LmNzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiO0FBQ0E7SUFDSSxvQ0FBb0M7SUFDcEMsZ0JBQWdCO0lBQ2hCLGtCQUFrQjtJQUNsQixtQkFBbUI7SUFDbkIsbUJBQW1CO0lBQ25CLGlCQUFpQjtJQUNqQixrQkFBa0I7SUFDbEIsWUFBWTtBQUNoQjs7QUFFQTtJQUNJLGlCQUFpQjtJQUNqQixRQUFRO0lBQ1IsU0FBUztJQUNULFNBQVM7QUFDYjs7QUFFQTtJQUNJLGlCQUFpQjtJQUNqQixRQUFRO0lBQ1IsU0FBUztJQUNULFNBQVM7SUFDVCxXQUFXO0lBQ1gsWUFBWTtBQUNoQjs7QUFFQTtJQUNJLGlCQUFpQjtJQUNqQixRQUFRO0lBQ1IsV0FBVztBQUNmOztBQUVBO0lBQ0ksaUJBQWlCO0lBQ2pCLFVBQVU7QUFDZDs7QUFFQTtJQUNJLGlCQUFpQjtJQUNqQixRQUFRO0lBQ1IsVUFBVTtBQUNkOztBQUVBO0lBQ0ksaUJBQWlCO0lBQ2pCLFVBQVU7QUFDZDs7QUFFQTtJQUNJLG9DQUFvQztJQUNwQyxXQUFXO0lBQ1gsZUFBZTtJQUNmLGdCQUFnQjtJQUNoQixnQkFBZ0I7SUFDaEIsa0JBQWtCO0lBQ2xCLG1CQUFtQjtJQUNuQixXQUFXO0lBQ1gsZ0NBQWdDO0lBQ2hDLGlCQUFpQjtJQUNqQixrQkFBa0I7QUFDdEIiLCJmaWxlIjoiY2FyZC5jb21wb25lbnQuY3NzIiwic291cmNlc0NvbnRlbnQiOlsiXHJcbiNjYXJkIHtcclxuICAgIGJhY2tncm91bmQtY29sb3I6IHJnYigyNTUsIDI1NSwgMjU1KTtcclxuICAgIG1hcmdpbi10b3A6IDE1cHg7XHJcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7XHJcbiAgICBib3JkZXItcmFkaXVzOiAxMHB4O1xyXG4gICAgYm9yZGVyLWNvbG9yOiBibGFjaztcclxuICAgIGJvcmRlci13aWR0aDogMXB4O1xyXG4gICAgYm9yZGVyLXN0eWxlOnNvbGlkO1xyXG4gICAgaGVpZ2h0OjExMHB4O1xyXG59XHJcblxyXG4jbmFtZSB7XHJcbiAgICBwb3NpdGlvbjphYnNvbHV0ZTtcclxuICAgIHRvcDoxMHB4O1xyXG4gICAgbGVmdDoxMHB4O1xyXG4gICAgd2lkdGg6NzAlO1xyXG59XHJcblxyXG4jZGVzY3JpcHRpb24ge1xyXG4gICAgcG9zaXRpb246YWJzb2x1dGU7XHJcbiAgICB0b3A6NDBweDtcclxuICAgIGxlZnQ6MTBweDtcclxuICAgIHdpZHRoOjk1JTtcclxuICAgIGhlaWdodDo1MHB4O1xyXG4gICAgcmVzaXplOiBub25lO1xyXG59XHJcblxyXG4jc2F2ZUVkaXQge1xyXG4gICAgcG9zaXRpb246YWJzb2x1dGU7XHJcbiAgICB0b3A6MTBweDtcclxuICAgIHJpZ2h0OjEzMHB4O1xyXG59XHJcblxyXG4jZGVsZXRlQnV0dG9uIHtcclxuICAgIHBvc2l0aW9uOmFic29sdXRlO1xyXG4gICAgcmlnaHQ6NjBweDtcclxufVxyXG5cclxuI2RvbmVCdXR0b24ge1xyXG4gICAgcG9zaXRpb246YWJzb2x1dGU7XHJcbiAgICB0b3A6MTBweDtcclxuICAgIHJpZ2h0OjEwcHg7XHJcbn1cclxuXHJcbiNmb2xkRXhwYW5kQXJyb3cge1xyXG4gICAgcG9zaXRpb246YWJzb2x1dGU7XHJcbiAgICByaWdodDo5MHB4O1xyXG59XHJcblxyXG4jY2FyZE1lc3NhZ2Uge1xyXG4gICAgYmFja2dyb3VuZC1jb2xvcjogcmdiKDI1NSwgMTg3LCAxODcpO1xyXG4gICAgbWFyZ2luOmF1dG87XHJcbiAgICBtYXJnaW4tdG9wOiA1cHg7XHJcbiAgICBtaW4td2lkdGg6IDQwMHB4O1xyXG4gICAgbWF4LXdpZHRoOiA4MDBweDtcclxuICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcclxuICAgIGJvcmRlci1yYWRpdXM6IDEwcHg7XHJcbiAgICBoZWlnaHQ6MzBweDtcclxuICAgIGJvcmRlci1jb2xvcjogcmdiKDI1NSwgMTg3LCAxODcpO1xyXG4gICAgYm9yZGVyLXdpZHRoOiAzcHg7XHJcbiAgICBib3JkZXItc3R5bGU6c29saWQ7XHJcbn1cclxuIl19 */"] });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](CardComponent, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
        args: [{
                selector: 'app-card',
                templateUrl: './card.component.html',
                styleUrls: ['./card.component.css']
            }]
    }], function () { return [{ type: _ngrx_store__WEBPACK_IMPORTED_MODULE_1__["Store"] }, { type: _services_task_services_update_service__WEBPACK_IMPORTED_MODULE_2__["UpdateService"] }, { type: _services_task_services_delete_service__WEBPACK_IMPORTED_MODULE_3__["DeleteService"] }]; }, { cardInput: [{
            type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"]
        }] }); })();


/***/ }),

/***/ "oDk3":
/*!**********************************************!*\
  !*** ./src/app/top-bar/top-bar.component.ts ***!
  \**********************************************/
/*! exports provided: TopBarComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "TopBarComponent", function() { return TopBarComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _models_form_panel_mode__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../models/form-panel-mode */ "Qa/w");
/* harmony import */ var _store_store_actions__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../store/store-actions */ "R5um");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _services_user_services_logOut_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../services/user_services/logOut.service */ "ynzv");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/common */ "ofXK");







function TopBarComponent_button_5_Template(rf, ctx) { if (rf & 1) {
    const _r4 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "button", 5);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function TopBarComponent_button_5_Template_button_click_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r4); const ctx_r3 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); return ctx_r3.logIn(); });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1, "Log In");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} }
function TopBarComponent_button_6_Template(rf, ctx) { if (rf & 1) {
    const _r6 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "button", 5);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function TopBarComponent_button_6_Template_button_click_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r6); const ctx_r5 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); return ctx_r5.signUp(); });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1, "Sign Up");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} }
function TopBarComponent_button_7_Template(rf, ctx) { if (rf & 1) {
    const _r8 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "button", 5);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function TopBarComponent_button_7_Template_button_click_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r8); const ctx_r7 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); return ctx_r7.logOut(); });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1, "Log Out");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} }
class TopBarComponent {
    constructor(store, logOutService) {
        this.store = store;
        this.logOutService = logOutService;
        this.barMessage = "";
        this.appState$ = store.select('appState');
        this.appState$.subscribe(app => this.setStates(app));
    }
    setStates(appState) {
        this.barMessage = appState.topBarMessage;
        var userLogged = appState.userLogged;
        if (userLogged) {
            this.logOutVisible = true;
            this.logInSignInVisible = false;
        }
        else {
            this.logOutVisible = false;
            this.logInSignInVisible = true;
        }
    }
    ngOnInit() {
    }
    logIn() {
        this.store.dispatch(Object(_store_store_actions__WEBPACK_IMPORTED_MODULE_2__["setFormPanelMode"])({ mode: _models_form_panel_mode__WEBPACK_IMPORTED_MODULE_1__["FormPanelMode"].LOG_IN }));
    }
    signUp() {
        this.store.dispatch(Object(_store_store_actions__WEBPACK_IMPORTED_MODULE_2__["setFormPanelMode"])({ mode: _models_form_panel_mode__WEBPACK_IMPORTED_MODULE_1__["FormPanelMode"].SIGN_IN }));
    }
    logOut() {
        this.logOutService.logoutUser();
    }
}
TopBarComponent.ɵfac = function TopBarComponent_Factory(t) { return new (t || TopBarComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_3__["Store"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_services_user_services_logOut_service__WEBPACK_IMPORTED_MODULE_4__["LogOutService"])); };
TopBarComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: TopBarComponent, selectors: [["app-top-bar"]], decls: 8, vars: 4, consts: [[1, "card", "m-2", "shadow", 2, "height", "70px"], [1, "card-body"], [1, "card-title"], [2, "float", "right", "position", "relative", "top", "-60px"], ["class", "btn btn-primary m-2", 3, "click", 4, "ngIf"], [1, "btn", "btn-primary", "m-2", 3, "click"]], template: function TopBarComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 0);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "h2", 2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](3);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](4, "div", 3);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](5, TopBarComponent_button_5_Template, 2, 0, "button", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](6, TopBarComponent_button_6_Template, 2, 0, "button", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](7, TopBarComponent_button_7_Template, 2, 0, "button", 4);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](3);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtextInterpolate"](ctx.barMessage);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](2);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx.logInSignInVisible);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx.logInSignInVisible);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx.logOutVisible);
    } }, directives: [_angular_common__WEBPACK_IMPORTED_MODULE_5__["NgIf"]], styles: ["#topBar[_ngcontent-%COMP%] {\r\n    background-color: rgb(255, 255, 255);\r\n    margin-top: 15px;\r\n    position: relative;\r\n    border-radius: 10px;\r\n    border-color: black;\r\n    border-width: 1px;\r\n    border-style:solid;\r\n}\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInRvcC1iYXIuY29tcG9uZW50LmNzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtJQUNJLG9DQUFvQztJQUNwQyxnQkFBZ0I7SUFDaEIsa0JBQWtCO0lBQ2xCLG1CQUFtQjtJQUNuQixtQkFBbUI7SUFDbkIsaUJBQWlCO0lBQ2pCLGtCQUFrQjtBQUN0QiIsImZpbGUiOiJ0b3AtYmFyLmNvbXBvbmVudC5jc3MiLCJzb3VyY2VzQ29udGVudCI6WyIjdG9wQmFyIHtcclxuICAgIGJhY2tncm91bmQtY29sb3I6IHJnYigyNTUsIDI1NSwgMjU1KTtcclxuICAgIG1hcmdpbi10b3A6IDE1cHg7XHJcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7XHJcbiAgICBib3JkZXItcmFkaXVzOiAxMHB4O1xyXG4gICAgYm9yZGVyLWNvbG9yOiBibGFjaztcclxuICAgIGJvcmRlci13aWR0aDogMXB4O1xyXG4gICAgYm9yZGVyLXN0eWxlOnNvbGlkO1xyXG59Il19 */"] });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](TopBarComponent, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
        args: [{
                selector: 'app-top-bar',
                templateUrl: './top-bar.component.html',
                styleUrls: ['./top-bar.component.css']
            }]
    }], function () { return [{ type: _ngrx_store__WEBPACK_IMPORTED_MODULE_3__["Store"] }, { type: _services_user_services_logOut_service__WEBPACK_IMPORTED_MODULE_4__["LogOutService"] }]; }, null); })();


/***/ }),

/***/ "peRI":
/*!**********************************************************!*\
  !*** ./src/app/services/task_services/update.service.ts ***!
  \**********************************************************/
/*! exports provided: UpdateService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "UpdateService", function() { return UpdateService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! rxjs/operators */ "kU1M");
/* harmony import */ var _store_store_actions__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../store/store-actions */ "R5um");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common/http */ "tk/3");
/* harmony import */ var _services_settings_tools__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../../services.settings.tools */ "snQ+");







class UpdateService {
    constructor(store, http, serviceSettings) {
        this.store = store;
        this.http = http;
        this.serviceSettings = serviceSettings;
        this.token = "";
        this.userLogged = false;
        this.appState$ = store.select('appState');
        this.appState$.subscribe(app => this.setState(app));
    }
    setState(state) {
        this.token = state.token;
        this.userLogged = this.userLogged;
    }
    updateTask(card) {
        console.log(card);
        if (this.serviceSettings.tokenReceived()) {
            this.http.put(this.serviceSettings.tasksUrl + this.token, this.serviceSettings.cardToTaskConverter(card), { observe: 'response' })
                .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_1__["catchError"])(error => this.serviceSettings.handleHttpError(error)))
                .subscribe(response => this.analyzeUpdateResponse(card, response));
        }
        else {
            this.addMessage(this.serviceSettings.tokenNotFoundMessage, false, 0);
        }
    }
    analyzeUpdateResponse(card, response) {
        if (response != null) {
            var status = response.status;
            if (response.body != null) {
                if (status == 202) {
                    this.store.dispatch(Object(_store_store_actions__WEBPACK_IMPORTED_MODULE_2__["updateCard"])({ card }));
                    if (response.body != null && response.status != null) {
                        this.addMessage(response.body.value, true, response.status);
                    }
                }
            }
        }
    }
    addMessage(message, messageStatus, messageStatusCode) {
        this.serviceSettings.addServerManagementMessage(message, messageStatus, messageStatusCode);
    }
}
UpdateService.ɵfac = function UpdateService_Factory(t) { return new (t || UpdateService)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_3__["Store"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpClient"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_services_settings_tools__WEBPACK_IMPORTED_MODULE_5__["ServicesSettingsAndTools"])); };
UpdateService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjectable"]({ token: UpdateService, factory: UpdateService.ɵfac, providedIn: 'root' });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](UpdateService, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"],
        args: [{
                providedIn: 'root'
            }]
    }], function () { return [{ type: _ngrx_store__WEBPACK_IMPORTED_MODULE_3__["Store"] }, { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpClient"] }, { type: _services_settings_tools__WEBPACK_IMPORTED_MODULE_5__["ServicesSettingsAndTools"] }]; }, null); })();


/***/ }),

/***/ "snQ+":
/*!********************************************!*\
  !*** ./src/app/services.settings.tools.ts ***!
  \********************************************/
/*! exports provided: ServicesSettingsAndTools */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ServicesSettingsAndTools", function() { return ServicesSettingsAndTools; });
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/common/http */ "tk/3");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var rxjs__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! rxjs */ "qCKp");
/* harmony import */ var _store_store_actions__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./store/store-actions */ "R5um");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @ngrx/store */ "l7P3");






class ServicesSettingsAndTools {
    constructor(store) {
        this.store = store;
        this.rootUrl = 'https://morning-coast-72770.herokuapp.com/toDo';
        this.tokenUrl = this.rootUrl + '/token';
        this.loginUrl = this.rootUrl + '/login';
        this.registerUrl = this.rootUrl + '/register/';
        this.tasksUrl = this.rootUrl + '/tasks/';
        this.tasksDoneUrl = this.rootUrl + '/tasks/done/';
        this.tasksTodoUrl = this.rootUrl + '/tasks/todo/';
        this.logOutUrl = this.rootUrl + '/logout/';
        this.acceptedTokenLength = 15;
        this.httpOptions = {
            headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_0__["HttpHeaders"]({ 'Content-Type': 'application/json' })
        };
        this.token = "";
        this.userLogged = false;
        this.tokenNotFoundMessage = "Cannot connect to server.";
        this.appState$ = store.select('appState');
        this.appState$.subscribe(app => this.setState(app));
    }
    setState(state) {
        this.token = state.token;
        this.userLogged = this.userLogged;
    }
    addServerManagementMessage(message, status, statusCode) {
        var serverMessage = { message: message, messageStatusCode: statusCode, messageStatus: status };
        this.store.dispatch(Object(_store_store_actions__WEBPACK_IMPORTED_MODULE_3__["addServerMessage"])({ message: serverMessage }));
    }
    cardToTaskConverter(card) {
        var task = { id: card.id, name: card.taskName, description: card.description, done: card.done };
        return task;
    }
    taskToCardConverter(task) {
        var card = { id: task.id, taskName: task.name, description: task.description, done: task.done,
            message: "", messageShow: false, editMode: true, folded: false };
        return card;
    }
    taskListToCardConverter(tasks) {
        var cards = [];
        for (var i = 0; i < tasks.length; i++) {
            var card = {
                id: tasks[i].id,
                taskName: tasks[i].name,
                description: tasks[i].description,
                done: tasks[i].done,
                message: "",
                messageShow: false,
                editMode: false,
                folded: true
            };
            cards.push(card);
        }
        return cards;
    }
    handleHttpError(error) {
        return new rxjs__WEBPACK_IMPORTED_MODULE_2__["Observable"](observer => {
            if (error.error instanceof ErrorEvent) {
                //general error
                this.addServerManagementMessage(error.error.message, false, error.status);
            }
            else {
                //backend error
                var errorValue = error.error.value;
                var errorStatus = error.status;
                this.addServerManagementMessage(errorValue, false, errorStatus);
            }
            observer.next(null);
        });
    }
    convertTasks(tasks) {
        return this.taskListToCardConverter(tasks);
    }
    tokenReceived() {
        if (this.token != null) {
            if (this.token.length == this.acceptedTokenLength) {
                return true;
            }
        }
        return false;
    }
    checkTokenLength(token) {
        if (token != null) {
            if (token.length == this.acceptedTokenLength) {
                return true;
            }
        }
        return false;
    }
}
ServicesSettingsAndTools.ɵfac = function ServicesSettingsAndTools_Factory(t) { return new (t || ServicesSettingsAndTools)(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵinject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_4__["Store"])); };
ServicesSettingsAndTools.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineInjectable"]({ token: ServicesSettingsAndTools, factory: ServicesSettingsAndTools.ɵfac, providedIn: 'root' });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵsetClassMetadata"](ServicesSettingsAndTools, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"],
        args: [{
                providedIn: 'root'
            }]
    }], function () { return [{ type: _ngrx_store__WEBPACK_IMPORTED_MODULE_4__["Store"] }]; }, null); })();


/***/ }),

/***/ "sxXX":
/*!*********************************************************!*\
  !*** ./src/app/services/user_services/token.service.ts ***!
  \*********************************************************/
/*! exports provided: TokenService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "TokenService", function() { return TokenService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! rxjs/operators */ "kU1M");
/* harmony import */ var _store_store_actions__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../store/store-actions */ "R5um");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/common/http */ "tk/3");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _services_settings_tools__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../../services.settings.tools */ "snQ+");







class TokenService {
    constructor(http, store, serviceSettings) {
        this.http = http;
        this.store = store;
        this.serviceSettings = serviceSettings;
        this.token = "";
        this.appState$ = store.select('appState');
        this.appState$.subscribe(app => this.token = app.token);
    }
    getToken() {
        console.log("get token called");
        this.addServerManagementMessage("Connecting to server...", true, 0);
        this.http.get(this.serviceSettings.tokenUrl, { observe: 'response' })
            .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_1__["catchError"])(error => this.serviceSettings.handleHttpError(error)))
            .subscribe(token => this.setToken(token));
    }
    setToken(response) {
        var _a;
        if (response != null) {
            var status = response.status;
            if (response.body != null) {
                if (status == 200) {
                    if (((_a = response.body) === null || _a === void 0 ? void 0 : _a.value) != null) {
                        if (this.serviceSettings.checkTokenLength(response.body.value)) {
                            this.addServerManagementMessage("Connected", true, 0);
                            this.store.dispatch(Object(_store_store_actions__WEBPACK_IMPORTED_MODULE_2__["setToken"])({ token: response.body.value }));
                            return;
                        }
                    }
                }
            }
        }
    }
    addServerManagementMessage(message, status, statusCode) {
        var serverMessage = { message: message, messageStatusCode: statusCode, messageStatus: status };
        this.store.dispatch(Object(_store_store_actions__WEBPACK_IMPORTED_MODULE_2__["addServerMessage"])({ message: serverMessage }));
    }
}
TokenService.ɵfac = function TokenService_Factory(t) { return new (t || TokenService)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_angular_common_http__WEBPACK_IMPORTED_MODULE_3__["HttpClient"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_4__["Store"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_services_settings_tools__WEBPACK_IMPORTED_MODULE_5__["ServicesSettingsAndTools"])); };
TokenService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjectable"]({ token: TokenService, factory: TokenService.ɵfac, providedIn: 'root' });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](TokenService, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"],
        args: [{
                providedIn: 'root'
            }]
    }], function () { return [{ type: _angular_common_http__WEBPACK_IMPORTED_MODULE_3__["HttpClient"] }, { type: _ngrx_store__WEBPACK_IMPORTED_MODULE_4__["Store"] }, { type: _services_settings_tools__WEBPACK_IMPORTED_MODULE_5__["ServicesSettingsAndTools"] }]; }, null); })();


/***/ }),

/***/ "wNDw":
/*!********************************************!*\
  !*** ./src/app/models/task-list-status.ts ***!
  \********************************************/
/*! exports provided: TaskListStatus */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "TaskListStatus", function() { return TaskListStatus; });
var TaskListStatus;
(function (TaskListStatus) {
    TaskListStatus[TaskListStatus["ALL"] = 0] = "ALL";
    TaskListStatus[TaskListStatus["DONE"] = 1] = "DONE";
    TaskListStatus[TaskListStatus["TODO"] = 2] = "TODO";
})(TaskListStatus || (TaskListStatus = {}));


/***/ }),

/***/ "wo+T":
/*!****************************************************!*\
  !*** ./src/app/form-panel/form-panel.component.ts ***!
  \****************************************************/
/*! exports provided: FormPanelComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "FormPanelComponent", function() { return FormPanelComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _models_form_panel_mode__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ../models/form-panel-mode */ "Qa/w");
/* harmony import */ var _store_store_actions__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../store/store-actions */ "R5um");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _services_user_services_login_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../services/user_services/login.service */ "Q1VL");
/* harmony import */ var _services_user_services_registration_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../services/user_services/registration.service */ "b0kU");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/common */ "ofXK");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/forms */ "3Pt+");









function FormPanelComponent_div_0_button_13_Template(rf, ctx) { if (rf & 1) {
    const _r4 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "button", 10);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function FormPanelComponent_div_0_button_13_Template_button_click_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r4); const ctx_r3 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2); return ctx_r3.logIn(); });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1, "Log In");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} }
function FormPanelComponent_div_0_button_14_Template(rf, ctx) { if (rf & 1) {
    const _r6 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "button", 10);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function FormPanelComponent_div_0_button_14_Template_button_click_0_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r6); const ctx_r5 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](2); return ctx_r5.signUp(); });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](1, "Sign Up");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} }
function FormPanelComponent_div_0_Template(rf, ctx) { if (rf & 1) {
    const _r8 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵgetCurrentView"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](0, "div", 1);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](1, "div", 2);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](2, "div");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](3, "div", 3);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](4, "label", 4);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](5, "Email address");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](6, "input", 5);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("ngModelChange", function FormPanelComponent_div_0_Template_input_ngModelChange_6_listener($event) { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r8); const ctx_r7 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); return ctx_r7.email = $event; });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](7, "div", 6);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](8, "We'll never share your email with anyone else.");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](9, "div", 3);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](10, "label", 7);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](11, "Password");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](12, "input", 8);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("ngModelChange", function FormPanelComponent_div_0_Template_input_ngModelChange_12_listener($event) { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r8); const ctx_r9 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); return ctx_r9.password = $event; });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](13, FormPanelComponent_div_0_button_13_Template, 2, 0, "button", 9);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](14, FormPanelComponent_div_0_button_14_Template, 2, 0, "button", 9);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementStart"](15, "button", 10);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵlistener"]("click", function FormPanelComponent_div_0_Template_button_click_15_listener() { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵrestoreView"](_r8); const ctx_r10 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"](); return ctx_r10.cancel(); });
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtext"](16, "Cancel");
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵelementEnd"]();
} if (rf & 2) {
    const ctx_r0 = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵnextContext"]();
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](6);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngModel", ctx_r0.email);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](6);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngModel", ctx_r0.password);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx_r0.logInButton);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵadvance"](1);
    _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx_r0.signUpButton);
} }
class FormPanelComponent {
    constructor(store, logInService, registrationService) {
        this.store = store;
        this.logInService = logInService;
        this.registrationService = registrationService;
        this.email = "";
        this.password = "";
        this.appState$ = store.select('appState');
        this.appState$.subscribe(app => this.setStates(app));
    }
    ngOnInit() {
    }
    setStates(appState) {
        switch (appState.formPanelMode) {
            case _models_form_panel_mode__WEBPACK_IMPORTED_MODULE_1__["FormPanelMode"].LOG_IN:
                this.clearForm();
                this.logInButton = true;
                this.signUpButton = false;
                this.formVisibility = true;
                break;
            case _models_form_panel_mode__WEBPACK_IMPORTED_MODULE_1__["FormPanelMode"].SIGN_IN:
                this.clearForm();
                this.logInButton = false;
                this.signUpButton = true;
                this.formVisibility = true;
                break;
            case _models_form_panel_mode__WEBPACK_IMPORTED_MODULE_1__["FormPanelMode"].NOT_VISIBLE:
                this.clearForm();
                this.formVisibility = false;
                break;
        }
    }
    logIn() {
        var credentials = { userEmail: this.email, userPassword: this.password };
        this.logInService.loginUser(credentials);
        this.clearForm();
    }
    signUp() {
        var credentials = { userEmail: this.email, userPassword: this.password };
        this.registrationService.registerUser(credentials);
        this.clearForm();
    }
    cancel() {
        this.clearForm();
        this.store.dispatch(Object(_store_store_actions__WEBPACK_IMPORTED_MODULE_2__["setFormPanelMode"])({ mode: _models_form_panel_mode__WEBPACK_IMPORTED_MODULE_1__["FormPanelMode"].NOT_VISIBLE }));
    }
    clearForm() {
        this.email = "";
        this.password = "";
    }
}
FormPanelComponent.ɵfac = function FormPanelComponent_Factory(t) { return new (t || FormPanelComponent)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_3__["Store"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_services_user_services_login_service__WEBPACK_IMPORTED_MODULE_4__["LoginService"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdirectiveInject"](_services_user_services_registration_service__WEBPACK_IMPORTED_MODULE_5__["RegistrationService"])); };
FormPanelComponent.ɵcmp = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineComponent"]({ type: FormPanelComponent, selectors: [["app-form-panel"]], decls: 1, vars: 1, consts: [["class", "card m-3 shadow", 4, "ngIf"], [1, "card", "m-3", "shadow"], [1, "card-body"], [1, "mb-3"], ["for", "exampleInputEmail1", 1, "form-label"], ["type", "email", "id", "exampleInputEmail1", "aria-describedby", "emailHelp", 1, "form-control", 3, "ngModel", "ngModelChange"], ["id", "emailHelp", 1, "form-text"], ["for", "exampleInputPassword1", 1, "form-label"], ["type", "password", "id", "exampleInputPassword1", 1, "form-control", 3, "ngModel", "ngModelChange"], ["type", "button", "class", "btn btn-primary m-2", 3, "click", 4, "ngIf"], ["type", "button", 1, "btn", "btn-primary", "m-2", 3, "click"]], template: function FormPanelComponent_Template(rf, ctx) { if (rf & 1) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵtemplate"](0, FormPanelComponent_div_0_Template, 17, 4, "div", 0);
    } if (rf & 2) {
        _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵproperty"]("ngIf", ctx.formVisibility);
    } }, directives: [_angular_common__WEBPACK_IMPORTED_MODULE_6__["NgIf"], _angular_forms__WEBPACK_IMPORTED_MODULE_7__["DefaultValueAccessor"], _angular_forms__WEBPACK_IMPORTED_MODULE_7__["NgControlStatus"], _angular_forms__WEBPACK_IMPORTED_MODULE_7__["NgModel"]], styles: ["\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJmb3JtLXBhbmVsLmNvbXBvbmVudC5jc3MifQ== */"] });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](FormPanelComponent, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"],
        args: [{
                selector: 'app-form-panel',
                templateUrl: './form-panel.component.html',
                styleUrls: ['./form-panel.component.css']
            }]
    }], function () { return [{ type: _ngrx_store__WEBPACK_IMPORTED_MODULE_3__["Store"] }, { type: _services_user_services_login_service__WEBPACK_IMPORTED_MODULE_4__["LoginService"] }, { type: _services_user_services_registration_service__WEBPACK_IMPORTED_MODULE_5__["RegistrationService"] }]; }, null); })();


/***/ }),

/***/ "ynzv":
/*!**********************************************************!*\
  !*** ./src/app/services/user_services/logOut.service.ts ***!
  \**********************************************************/
/*! exports provided: LogOutService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LogOutService", function() { return LogOutService; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var rxjs_operators__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! rxjs/operators */ "kU1M");
/* harmony import */ var _store_store_actions__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../store/store-actions */ "R5um");
/* harmony import */ var _ngrx_store__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ngrx/store */ "l7P3");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common/http */ "tk/3");
/* harmony import */ var _services_settings_tools__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../../services.settings.tools */ "snQ+");
/* harmony import */ var _token_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./token.service */ "sxXX");








class LogOutService {
    constructor(store, http, serviceSettings, tokenService) {
        this.store = store;
        this.http = http;
        this.serviceSettings = serviceSettings;
        this.tokenService = tokenService;
        this.token = "";
        this.userLogged = false;
        this.appState$ = store.select('appState');
        this.appState$.subscribe(app => this.setState(app));
    }
    setState(state) {
        this.token = state.token;
        this.userLogged = this.userLogged;
    }
    logoutUser() {
        this.addMessage("Logging out user...", true, 0);
        if (this.serviceSettings.tokenReceived()) {
            this.http.get(this.serviceSettings.logOutUrl + this.token, { observe: 'response' })
                .pipe(Object(rxjs_operators__WEBPACK_IMPORTED_MODULE_1__["catchError"])(error => this.serviceSettings.handleHttpError(error)))
                .subscribe(response => {
                this.analyzeLogoutResponse(response);
            });
        }
        else {
            var message = 'Token not found.';
            this.addMessage(this.serviceSettings.tokenNotFoundMessage, false, 0);
        }
    }
    analyzeLogoutResponse(response) {
        if (response != null) {
            var status = response.status;
            if (response.body != null) {
                var message = response.body.value;
                if (status == 202) {
                    this.executeLogOutOperations(message, status);
                }
            }
        }
    }
    executeLogOutOperations(message, status) {
        this.addMessage(message, true, status);
        this.store.dispatch(Object(_store_store_actions__WEBPACK_IMPORTED_MODULE_2__["setUserLoggedToFalse"])());
        this.store.dispatch(Object(_store_store_actions__WEBPACK_IMPORTED_MODULE_2__["setToken"])({ token: "" }));
        this.store.dispatch(Object(_store_store_actions__WEBPACK_IMPORTED_MODULE_2__["setTopBarMessage"])({ message: "ToDo App" }));
        this.store.dispatch(Object(_store_store_actions__WEBPACK_IMPORTED_MODULE_2__["createMultipleCards"])({ cards: [] }));
        this.tokenService.getToken();
    }
    addMessage(message, status, statusCode) {
        this.serviceSettings.addServerManagementMessage(message, status, statusCode);
    }
}
LogOutService.ɵfac = function LogOutService_Factory(t) { return new (t || LogOutService)(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_ngrx_store__WEBPACK_IMPORTED_MODULE_3__["Store"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpClient"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_services_settings_tools__WEBPACK_IMPORTED_MODULE_5__["ServicesSettingsAndTools"]), _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵinject"](_token_service__WEBPACK_IMPORTED_MODULE_6__["TokenService"])); };
LogOutService.ɵprov = _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵɵdefineInjectable"]({ token: LogOutService, factory: LogOutService.ɵfac, providedIn: 'root' });
/*@__PURE__*/ (function () { _angular_core__WEBPACK_IMPORTED_MODULE_0__["ɵsetClassMetadata"](LogOutService, [{
        type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"],
        args: [{
                providedIn: 'root'
            }]
    }], function () { return [{ type: _ngrx_store__WEBPACK_IMPORTED_MODULE_3__["Store"] }, { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpClient"] }, { type: _services_settings_tools__WEBPACK_IMPORTED_MODULE_5__["ServicesSettingsAndTools"] }, { type: _token_service__WEBPACK_IMPORTED_MODULE_6__["TokenService"] }]; }, null); })();


/***/ }),

/***/ "zUnb":
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "fXoL");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! ./environments/environment */ "AytR");
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app/app.module */ "ZAI4");
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/platform-browser */ "jhN1");




if (_environments_environment__WEBPACK_IMPORTED_MODULE_1__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["enableProdMode"])();
}
_angular_platform_browser__WEBPACK_IMPORTED_MODULE_3__["platformBrowser"]().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_2__["AppModule"])
    .catch(err => console.error(err));


/***/ }),

/***/ "zn8P":
/*!******************************************************!*\
  !*** ./$$_lazy_route_resource lazy namespace object ***!
  \******************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncaught exception popping up in devtools
	return Promise.resolve().then(function() {
		var e = new Error("Cannot find module '" + req + "'");
		e.code = 'MODULE_NOT_FOUND';
		throw e;
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "zn8P";

/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main.js.map