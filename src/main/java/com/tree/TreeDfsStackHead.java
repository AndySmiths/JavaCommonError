package com.tree;

import java.util.Iterator;
import java.util.stream.Stream;


/**
 * Stream friendly immutable linked stack for DFS tree traversal
 * Instances should be treat immutable. DO NOT reassign its fields, use constructor only.
 * @param <T>
 */
public class TreeDfsStackHead<T> {
    TreeNode<T> node;
    Iterator<T> siblingIterator; // An iterator from the children stream to find next sibling node
    Stream<T> stream; // Keep track of the original children stream for clean up
    TreeDfsStackHead<T> previousHead;

    TreeDfsStackHead(TreeNode<T> node, Iterator<T> siblingIterator, Stream<T> stream, TreeDfsStackHead<T> previousHead) {
        this.node = node;
        this.stream = stream;
        this.siblingIterator = siblingIterator;
        this.previousHead = previousHead;
    }
}
