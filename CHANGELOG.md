# Changelog

All notable changes to BubbleVillagers will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [2.1.8] - 2026-07-20

### Fixed
- **Paper, Folia, and Purpur startup compatibility** — Replaced the optional Adventure SLF4J component logger with BubbleVillagers' server-neutral logger. This removes the `NoSuchMethodError: Services.service(ServiceLoader, Class)` crash reported when enabling v2.1.5 on Folia-compatible forks.
- Removed the obsolete custom Adventure SLF4J logger provider, ANSI serializer, and their dependencies. The plugin no longer initializes Adventure's optional service-loader logger implementation.

### Updated
- **Paper API** — Updated the compilation target to `26.1.2.build.74-stable`, the latest stable Paper API release. Folia and Purpur use this compatible Paper API surface; MorePaperLib continues to provide Folia-safe scheduling.

## [2.1.7] - 2026-06-05

### Changed
- **Adventure API** — Removed shaded `adventure-platform-bukkit` (not needed on Paper/Folia 26.1.2) and switched `adventure-text-minimessage`, `adventure-text-serializer-ansi`, and `adventure-text-logger-slf4j` from shaded to `provided` scope, using Adventure 5.1.0 bundled in the server. This is the correct long-term fix for the `NoSuchMethodError` crash introduced in v2.1.5.
- **KyoriUtil** — Replaced `BukkitAudiences.sender().sendMessage/sendActionBar()` with direct `Audience` cast (Paper 26.1.2's `CommandSender` natively implements `Audience`).
- Removed `net.kyori` relocation from the shade plugin since Adventure is no longer shaded.

## [2.1.6] - 2026-06-05

### Fixed
- **Folia 26.1.2 startup crash** — `NoSuchMethodError: Services.service(ServiceLoader, Class)` caused by Adventure API version mismatch. Reverted `adventure-text-minimessage`, `adventure-text-serializer-ansi`, and `adventure-text-logger-slf4j` from `5.1.0` back to `4.26.1` to maintain compatibility with `adventure-platform-bukkit:4.4.1`.

## [2.1.5] - 2026-05-11

### Updated
- **Paper API** updated to `26.1.2.build.63-stable` (latest stable build)
- **Adventure API** (`adventure-text-minimessage`, `adventure-text-serializer-ansi`, `adventure-text-logger-slf4j`) updated from `4.26.1` to `5.1.0`
- **MockBukkit** updated from `4.108.0` to `4.110.0`
- Updated `api-version` in `plugin.yml` to `26.1.2`

## [2.1.4] - 2026-05-02

### ✨ Added
- Stable release for Paper 26.1.2 channel readiness.

### 🔄 Changed
- Bumped plugin version from `2.1.3-BETA` to `2.1.4`.
- Updated Paper API target to stable build `26.1.2.build.53-stable`.
- Removed beta tag from plugin runtime description in `plugin.yml`.

### 🧪 Testing
- Verified build with Java 25 and Maven 3.9.11.
- Ran Maven test lifecycle successfully (`mvn clean test`).

## [2.1.3-BETA] - 2026-04-15

> ⚠️ **BETA RELEASE** — Targets Paper 26.1.x.

### 🐛 Fixed
- Removed XSeries dependency entirely — all XSeries versions (11.x–13.x) fail to parse the Minecraft 26.x version string (`26.1.2-2570-e64b1b2`) and crash with `ExceptionInInitializerError` on plugin enable.
- Replaced `XMaterial`, `XEntityType`, and `XPotion` usages with direct Bukkit API constants (`Material.*`, `EntityType.*`, `PotionEffectType.*`).

---

## [2.1.2-BETA] - 2026-04-15

> ⚠️ **BETA RELEASE** — Targets Paper 26.1.x.

### 🐛 Fixed
- Updated XSeries from `11.3.0` to `13.6.0` — XSeries 11.x could not parse the new Minecraft 26.x major version number and crashed on plugin enable. Version 13.x handles the new versioning scheme correctly.
- Updated shade exclusions in pom: `SkullCacheListener` was removed in XSeries 12.x and `NMSExtras` moved to `com.cryptomorin.xseries.reflection.minecraft`.

---

## [2.1.1-BETA] - 2026-04-15

> ⚠️ **BETA RELEASE** — Targets Paper 26.1.x.

### 🐛 Fixed
- Fixed plugin failing to load with `IllegalArgumentException: API version string should be of format "major.minor.patch" or "major.minor"` — changed `api-version` in `plugin.yml` from `'26'` to `'26.1'`.

---

## [2.1.0-BETA] - 2026-04-14

> ⚠️ **BETA RELEASE** — Targets Paper 26.1.x. Back up your worlds before using.

### ✨ Added
- Beta support for Paper 26.1.x (new Minecraft versioning).
- Plugin description now indicates BETA and target Paper version at runtime.

### 🔄 Changed
- Updated target Paper API from `1.21.10-R0.1-SNAPSHOT` to `26.1.2.build.53-stable` (new PaperMC release versioning).
- Updated Java source/target from **17** to **25** (required by Minecraft 26.1.x class files).
- Updated `api-version` in `plugin.yml` from `'1.21'` to `'26'`.
- Updated Adventure libraries to `4.26.1` (matching the Paper 26.x bundled Adventure BOM).
- Updated `adventure-platform-bukkit` to `4.4.1`.
- Updated `maven-shade-plugin` to `3.6.2` (adds Java 25 class file support).
- Updated MockBukkit to `4.108.0` (latest `mockbukkit-v1.21` while awaiting `mockbukkit-v26`).

### 🧪 Testing
- Disabled MockBukkit-based tests (`PluginLifecycleTest`, `AutoOptimizeModesTest`, `ClearGlowCommandTest`) with `@Disabled` annotations — MockBukkit does not yet support Paper 26.x registry changes. Tests will be re-enabled once `mockbukkit-v26` is published.

### 🔒 Known Limitations (Beta)
- Paper 26.1.x support remains marked beta in this plugin; validate on staging before production rollout.
- MockBukkit automated tests are pending `mockbukkit-v26` support.

## [2.0.2] - 2026-01-08

### ✨ Added
- Added `/villageroptimizer clearglow` admin subcommand to remove glow from optimized villagers in loaded chunks.
- Added `/villageroptimizer glow <on|off>` admin subcommand to enable/disable optimized villager glow at runtime.
- Added two new config-driven auto-optimization modes:
  - Auto-optimize when a chunk exceeds a villager population threshold.
  - Auto-optimize when a trade hall (radius) reaches a villager population threshold.

### 🔄 Changed
- When enabled, optimized villager glow is now applied for already-optimized villagers in loaded chunks and newly loaded chunks (not only at the moment of optimization).

### 🐛 Fixed
- Fixed “optimized villagers stay glowing after restart” when the outline feature is disabled by clearing glow on startup/chunk-load.

### 🧪 Testing
- Added MockBukkit-based tests for plugin lifecycle and key optimization/glow behaviors.

## [2.0.1] - 2026-01-08

### 🐛 Fixed
- Fixed `ClassCastException` on some Paper builds when reading `gameplay.restock-optimized-trades.restock-times` (e.g. `Integer` vs `Long`) which could error when right-clicking optimized villagers.

## [2.0.0] - 2024-12-15

### 🎉 Major Release - Rebranded as BubbleVillagers

#### ✨ Added
- Complete rebrand from VillagerOptimizer to BubbleVillagers
- New permission system with `bubblevillagers.*` namespace
- Backward compatibility with legacy `villageroptimizer.*` permissions
- Enhanced command aliases (`/bv`, `/bvopt`, `/bvunopt`)
- Comprehensive README.md with detailed documentation
- Modern Maven project structure
- Updated build configuration for Minecraft 1.21.x
- Java 17 support with modern dependency versions

#### 🔄 Changed
- Updated to Minecraft API version 1.21.4
- Upgraded Paper API to latest stable version
- Updated Caffeine cache library to 3.1.8 (Java 11+ version)
- Updated XSeries to 11.3.0 for better cross-version support
- Updated Adventure libraries to 4.17.0
- Updated bStats to 3.1.0
- Updated morepaperlib to 0.4.4 for improved Folia support
- Improved plugin.yml with better command descriptions
- Enhanced permission hierarchy with wildcard support

#### 🐛 Fixed
- Fixed typo in plugin.yml description ("Villager optimser" → "optimizer")
- Fixed command usage formatting for better clarity
- Improved permission-message readability

#### 📚 Documentation
- Added comprehensive README.md for Spigot and Modrinth
- Added CHANGELOG.md for version tracking
- Added detailed feature list and usage examples
- Added installation and configuration guides
- Added multi-language support documentation

#### 🔧 Technical Improvements
- Modernized Maven POM with latest plugin versions
- Enhanced shade plugin configuration
- Better artifact filtering and relocation
- Improved build reproducibility
- Added proper source and target Java version (17)
- Updated maven-compiler-plugin to 3.13.0
- Updated maven-shade-plugin to 3.6.0

---

## [1.6.2-bubble] - Previous Version

### Legacy VillagerOptimizer Release

#### Features
- Basic villager optimization system
- Nametag-based optimization
- Block interaction optimization
- Workstation optimization
- Command-based radius optimization
- Multi-language support (6 languages)
- Folia compatibility
- Trade restocking system
- Cooldown management

#### Supported Languages
- English (en_us)
- German (de_de)
- Italian (it_it)
- Korean (ko_kr)
- Russian (ru_ru)
- Chinese (zh_cn)

---

## Migration Notes

### From VillagerOptimizer 1.6.2 to BubbleVillagers 2.0.0

**No Configuration Changes Required** - All existing configurations are compatible.

**Permission Updates** (Optional, but recommended):
- Old: `villageroptimizer.admin` → New: `bubblevillagers.admin`
- Old: `villageroptimizer.optimize` → New: `bubblevillagers.optimize`
- Old: `villageroptimizer.unoptimize` → New: `bubblevillagers.unoptimize`

**Legacy permissions are still supported** for backward compatibility.

**Command Aliases**:
New aliases added, old aliases still work:
- `/bv` - Quick access to admin commands
- `/bvopt` - Quick optimization command
- `/bvunopt` - Quick unoptimization command

---

## Upcoming Features

### Planned for 2.1.0
- [ ] Web-based configuration interface
- [ ] Advanced statistics and metrics dashboard
- [ ] Villager group management
- [ ] Custom optimization profiles
- [ ] PlaceholderAPI integration
- [ ] WorldGuard region integration
- [ ] Economy integration for paid optimization

### Under Consideration
- [ ] Automatic optimization based on TPS
- [ ] Villager protection zones
- [ ] Custom optimization sounds and effects
- [ ] MySQL/SQLite data storage
- [ ] Cross-server villager synchronization

---

## Support

For bug reports, feature requests, or questions:
- **GitHub Issues**: [Create an issue](https://github.com/xGinko/VillagerOptimizer/issues)
- **Discord**: Join our community server (if available)
- **Spigot**: Visit our [Spigot page](https://www.spigotmc.org/)
- **Modrinth**: Check our [Modrinth page](https://modrinth.com/)

---

**Legend**:
- ✨ Added - New features
- 🔄 Changed - Changes in existing functionality
- 🐛 Fixed - Bug fixes
- 📚 Documentation - Documentation changes
- 🔧 Technical - Technical/infrastructure changes
- 🎉 Major - Major release milestone
