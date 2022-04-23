# Java Monorepo

<p align="left">
  <img alt="Java Monorepo" src="https://github.com/claudiney-silva/java-monorepo/workflows/Build,%20Image%20and%20Deploy/badge.svg">
</p>

## Sobre

Exemplo de **Monorepo** com **Maven/Springboot** e deploy em **K8S** com **Helm Chart** utilizando **Github Actions**.

## Git flow

![Gitflow utilizado](docs/gitflow.png)

---

# Istio (Service Mesh)

Passos para instalação do Istio e visualização dos serviços via Kiali.

Requisitos:
- Cluster Kubernetes com permissões elevadas (usaremos o Kind)
- Kubectl
- Helm

## Kind install and custer creation
```
curl -Lo ./kind https://kind.sigs.k8s.io/dl/v0.12.0/kind-linux-amd64
chmod +x ./kind
sudo mv ./kind /usr/local/bin/kind
kind create cluster --name my-cluster
```

## Kubectl install
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
kubectl version --client
```

## Helm install
```
wget https://get.helm.sh/helm-v3.8.2-linux-amd64.tar.gz
tar xvf helm-v3.8.2-linux-amd64.tar.gz
sudo mv linux-amd64/helm /usr/local/bin
rm helm-v3.8.2-linux-amd64.tar.gz
rm -rf linux-amd64
helm version
```

## Istio install

### Download
```
curl -L https://istio.io/downloadIstio | sh -
cd istio-1.13.3
export PATH=$PWD/bin:$PATH
```

### Install on Kind cluster
```
kubectl create namespace istio-system
helm install istio-base manifests/charts/base -n istio-system
helm install istiod manifests/charts/istio-control/istio-discovery -n istio-system
helm install istio-ingress manifests/charts/gateways/istio-ingress -n istio-system
helm install istio-egress manifests/charts/gateways/istio-egress -n istio-system
kubectl get pods -n istio-system
```

### Kiali, Prometheus, Grafana, etc install
```
kubectl apply -f samples/addons
kubectl get svc -n istio-system
kubectl port-forward svc/kiali -n istio-system 20001
```

Para acessar a interface do Kiali [clique aqui](http://localhost:20001).

## Java-Monorepo Install

O Helm do monorepo deve ser instalado após a instalação do **Istio** para que o proxy **envoy** seja injetado nos PODs.
```
kubectl create namespace java-monorepo
kubectl label namespace java-monorepo istio-injection=enabled
helm install java-monorepo ./.helm/app -n java-monorepo
kubectl get pods -n java-monorepo
kubectl port-forward svc/app-bar -n java-monorepo 8080:80
```

Para acessar o app-bar [clique aqui](http://localhost:8080/app-bar/track).

---

# JMeter (Teste de carga)

Na pipeline são executados os testes, mas você pode executá-los manualmente: 

## Download and Plan Test

Faça o [download](https://jmeter.apache.org/download_jmeter.cgi) do JMeter e crie um plano de testes pela ferramenta visual.

## Comando para executar o Plano de Teste

Na pasta `/bin` do JMeter execute o comando:

```
./jmeter.sh -n -t "/PATH-PROJETO/java-monorepo/apps/bar/src/main/resources/simple.jmx" -l "/PATH-PROJETO/java-monorepo/apps/bar/target/jmeter/results.csv" -e -o "/PATH-PROJETO/java-monorepo/apps/bar/target/jmeter/output"
```