package sample

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ListItemController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ListItem.list(params), model:[listItemCount: ListItem.count()]
    }

    def show(ListItem listItem) {
        respond listItem
    }

    @Transactional
    def save(ListItem listItem) {
        if (listItem == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }
        listItem.validate()
        if (listItem.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond listItem.errors, view:'create'
            return
        }

        listItem.save flush:true

        respond listItem, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(ListItem listItem) {
        if (listItem == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }
        listItem.validate()

        if (listItem.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond listItem.errors, view:'edit'
            return
        }

        listItem.save flush:true

        respond listItem, [status: OK, view:"show"]
    }

    @Transactional
    def delete(ListItem listItem) {

        if (listItem == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        listItem.delete flush:true

        render status: NO_CONTENT
    }
}
