#!/bin/sh
kubectl apply --filename toys-bestseller/configmap.yml
kubectl apply --filename toys-bestseller/target/classes/META-INF/dekorate/kubernetes.yml
kubectl apply --filename fashion-bestseller/target/classes/META-INF/dekorate/kubernetes.yml
kubectl apply --filename hot-deals/target/classes/META-INF/dekorate/kubernetes.yml
kubectl apply --filename gateway/target/classes/META-INF/dekorate/kubernetes.yml
