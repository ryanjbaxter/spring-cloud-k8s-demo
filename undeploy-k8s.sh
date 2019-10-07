#!/bin/sh
kubectl delete --filename toys-bestseller/configmap.yml
kubectl delete --filename toys-bestseller/target/classes/META-INF/dekorate/kubernetes.yml
kubectl delete --filename fashion-bestseller/target/classes/META-INF/dekorate/kubernetes.yml
kubectl delete --filename hot-deals/target/classes/META-INF/dekorate/kubernetes.yml
kubectl delete --filename gateway/target/classes/META-INF/dekorate/kubernetes.yml
kubectl delete service gateway-lb
