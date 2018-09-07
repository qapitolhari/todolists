package sample


import grails.rest.*

@Resource(readOnly = false, formats = ['json', 'xml'])
class ListItem {
    String itemText = "New Item"
    boolean done = false

    static belongsTo = [todoList: TodoList]
}