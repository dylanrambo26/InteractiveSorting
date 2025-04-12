// src/components/PartitionTree.jsx
import React from "react";
import "../tree.css";

const PartitionNode = ({ partition, array }) => {
    if (!partition) return null;

    // Slice the subarray represented by this partition
    const slice = array.slice(partition.left, partition.right + 1).join(", ");

    return (
        <li>
            <div className="node">[{slice}]</div>
            {(partition.leftChild || partition.rightChild) && (
                <ul>
                    {partition.leftChild && (
                        <PartitionNode partition={partition.leftChild} array={array} />
                    )}
                    {partition.rightChild && (
                        <PartitionNode partition={partition.rightChild} array={array} />
                    )}
                </ul>
            )}
        </li>
    );
};

const PartitionTree = ({ rootPartition, array }) => {
    if (!rootPartition) return null;

    return (
        <div className="tree mt-10">
            <ul>
                <PartitionNode partition={rootPartition} array={array} />
            </ul>
        </div>
    );
};

export default PartitionTree;
