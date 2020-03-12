package main

import (
	"fmt"
	//"time"
	"log"

	_ "github.com/mbobakov/grpc-consul-resolver" // It's important

	"google.golang.org/grpc"
)
//注册api测试

func main()  {

	fmt.Println("consul register test")

	conn, err := grpc.Dial(
		"consul://127.0.0.1:8500/whoami?wait=14s&tag=manual",
		grpc.WithInsecure(),
		grpc.WithBalancerName("round_robin"),
	)
	if err != nil {
		log.Fatal(err)
	}
	defer conn.Close()


}