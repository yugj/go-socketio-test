package main

import (
	"fmt"
	"log"
	"net/http"
)

/**
 * get param test
 */
func index(w http.ResponseWriter, r *http.Request) {
	query := r.URL.Query()
	hell := query["hell"][0]

	log.Println(hell)
	fmt.Fprintf(w, "Hello golang http!")
}

//simple http server
func main() {
	http.HandleFunc("/", index)

	err := http.ListenAndServe(":8888", nil)
	if err != nil {
		log.Fatal("ListenAndServe: ", err)
	}
}
