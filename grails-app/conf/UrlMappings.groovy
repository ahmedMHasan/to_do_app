class UrlMappings {

    static mappings = {
        // Default RESTful mapping for Task CRUD operations
        "/tasks"(resources: 'task')

        // Default homepage redirects to Task index
        "/"(controller: 'task', action: 'index')

        // Error pages
        "500"(view: '/error')
        "404"(view: '/notFound')

        // Catch-all route for other controller/action requests
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here if needed
            }
        }
    }
}
