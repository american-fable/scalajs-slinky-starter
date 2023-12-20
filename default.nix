# nixos-unstable. Find the current hash at https://status.nixos.org/.
# Check https://nodejs.org/en/download for the current npm release.
{ nixpkgs ? import (fetchTarball "https://github.com/NixOS/nixpkgs/archive/91a00709aebb3602f172a0bf47ba1ef013e34835.tar.gz") {config.allowUnfree = true;}
}:

let
  pkgs = [
    nixpkgs.docker
    nixpkgs.docker-compose
    nixpkgs.git
    nixpkgs.jdk
    nixpkgs.nodejs_20
    nixpkgs.openssh
    nixpkgs.sbt
    nixpkgs.scala
    nixpkgs.which
  ];

in
  nixpkgs.stdenv.mkDerivation {
    name = "crdt-experiments";
    buildInputs = pkgs;
  }
