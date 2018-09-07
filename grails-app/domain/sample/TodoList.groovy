package sample


import grails.rest.*

@Resource(readOnly = false, formats = ['json', 'xml'])
class TodoList {
    String listName

    static hasMany = [items: ListItem]

}