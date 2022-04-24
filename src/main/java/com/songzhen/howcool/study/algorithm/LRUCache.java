package com.songzhen.howcool.study.algorithm;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/***
 * LRU算法 是缓存的一种淘汰策略
 */
public class LRUCache {

    // 缓存容量
    private int capacity;
    // hashMap用于存储数据
    private HashMap<Integer,Node<Integer,Integer>> map;
    // 链表数据维护访问次数 1：最近访问的放在头 ，2：缓存达到限制的时候，移除链表尾节
    private DoubleLinkedList doubleLinkedList;



    public LRUCache(int capacity) {
        this.capacity=capacity;
        map = new HashMap<>();
        doubleLinkedList =new DoubleLinkedList();
    }

    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }

        Node<Integer,Integer> node = map.get(key);
        // 访问的数据node,移动到链表的头
        doubleLinkedList.removeNode(node);
        doubleLinkedList.addHead(node);

        return node.value;
    }

    public void put (int key  ,int value){
        if(map.containsKey(key)){
            Node<Integer,Integer> node = map.get(key);
            node.value=value;

            doubleLinkedList.removeNode(node);
            doubleLinkedList.addHead(node);
        }else {
            if(map.size()>=capacity){
                Node<Integer,Integer> lastNode = doubleLinkedList.getLastNode();
                doubleLinkedList.removeNode(lastNode);
                map.remove(lastNode.key);

            }
            Node<Integer,Integer> newNode = new Node<>(key,value);
            map.put(key,newNode);
            doubleLinkedList.addHead(newNode);
        }

    }

    class Node<K,V>{
        K key;
        V value;
        Node<K,V> pre;
        Node<K,V> next;

        public Node() {
            this.pre=this.next=null;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.pre=this.next=null;
        }
    }

    class DoubleLinkedList<K,V>{
        Node<K,V> head;
        Node<K,V> tail;

        public DoubleLinkedList() {
            head = new Node<>();
            tail = new Node<>();
            head.next =tail;
            tail.pre = head;
        }

        // 添加到头
        public void addHead(Node<K,V> node){
            node.next=head.next;
            node.pre=head;

            head.next.pre=node;
            head.next=node;
        }

        // 移除元素
        public void removeNode(Node<K,V> node){
            node.next.pre = node.pre;
            node.pre.next = node.next;
            node.pre=null;
            node.next=null;
        }

        public Node<K,V> getLastNode(){
            return tail.pre;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCacheDemo = new LRUCache(3);
        lruCacheDemo.put(1,1);
        lruCacheDemo.put(2, 2);
        lruCacheDemo.put(3, 3);

        // 1：预期：容量达到限制，淘汰尾节点1，打印：[1, 2, 3]
        System.out.println("lruCacheDemo.keySet() = " + lruCacheDemo.map.keySet());

        // 2：预期：容量达到限制，淘汰尾节点1，打印：[2, 3, 4]
        lruCacheDemo.put(4, 4);
        System.out.println("lruCacheDemo.keySet() = " + lruCacheDemo.map.keySet());

        // 3：预期：访问4的数据，最小访问，加入头节点，打印：[2, 3, 4]
        lruCacheDemo.get(2);
        System.out.println("lruCacheDemo.keySet() = " + lruCacheDemo.map.keySet());

        // 4：预期：加入5，达到限制,淘汰最久访问的缓存3（如果不执行第三步的话淘汰的应该是2），打印：[2, 4, 5]
        lruCacheDemo.put(5, 5);
        System.out.println("lruCacheDemo.keySet() = " + lruCacheDemo.map.keySet());
    }
}
