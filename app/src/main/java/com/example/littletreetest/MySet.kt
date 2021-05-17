package com.example.littletreetest

class MySet<T>(val helperSet: HashSet<T>) : Set<T> by helperSet {

}