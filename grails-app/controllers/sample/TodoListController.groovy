package sample

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TodoListController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TodoList.list(params), model:[todoListCount: TodoList.count()]
    }

    def show(TodoList todoList) {
        respond todoList
    }

    @Transactional
    def save(TodoList todoList) {
        if (todoList == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }
        todoList.validate()
        if (todoList.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond todoList.errors, view:'create'
            return
        }

        todoList.save flush:true

        respond todoList, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(TodoList todoList) {
        if (todoList == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }
        todoList.validate()
        if (todoList.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond todoList.errors, view:'edit'
            return
        }

        todoList.save flush:true

        respond todoList, [status: OK, view:"show"]
    }

    @Transactional
    def delete(TodoList todoList) {

        if (todoList == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        todoList.delete flush:true

        render status: NO_CONTENT
    }
}
