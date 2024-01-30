# nixos-unstable. Find the current hash at https://status.nixos.org/.
# Check https://nodejs.org/en/download for the current npm release.
{ nixpkgs ? import (fetchTarball "https://github.com/NixOS/nixpkgs/archive/56911ef3403a9318b7621ce745f5452fb9ef6867.tar.gz") {config.allowUnfree = true;}
}:

let
  pkgs = [
    nixpkgs.docker
    nixpkgs.docker-compose
    nixpkgs.git
    nixpkgs.adoptopenjdk-hotspot-bin-11
    nixpkgs.nodejs_20
    nixpkgs.openssh
    nixpkgs.scala
    nixpkgs.which
    (nixpkgs.sbt.override { jre = nixpkgs.adoptopenjdk-hotspot-bin-11; })
  ];

in
  nixpkgs.stdenv.mkDerivation {
    name = "crdt-experiments";
    buildInputs = pkgs;
  }
