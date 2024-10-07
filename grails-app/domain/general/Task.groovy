package general

class Task {
    String title
    String description
    Boolean completed = false
    Date dueDate

    static constraints = {
        title blank: false, maxSize: 255
        description nullable: true, maxSize: 1000
        completed nullable: false
        dueDate nullable: true
    }
}