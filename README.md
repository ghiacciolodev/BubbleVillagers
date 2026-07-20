# BubbleVillagers

[![Supports Folia](https://img.shields.io/badge/Supports-Folia-blue)](https://papermc.io/software/folia)
[![Minecraft Version](https://img.shields.io/badge/Minecraft-26.1.x-green)](https://www.minecraft.net/)
[![Java Version](https://img.shields.io/badge/Java-25-orange)](https://adoptium.net/)

**BubbleVillagers** (formerly VillagerOptimizer) is a powerful performance optimization plugin that helps combat heavy villager lag by allowing players and administrators to optimize their trading halls and villager setups.

## ✨ Features

### 🚀 Performance Optimization
- **AI Toggle System**: Remove AI from villagers to reduce server load while maintaining trading functionality
- **Smart Optimization**: Villagers can still restock trades and level up even when optimized
- **Configurable Cooldowns**: Prevent spam optimization with customizable cooldowns
- **Radius Commands**: Optimize or unoptimize multiple villagers at once in a specified radius

### 🎮 Multiple Optimization Methods
1. **Nametag Optimization**: Use nametags on villagers to optimize them
2. **Block Interaction**: Right-click villagers with specific blocks (configurable)
3. **Workstation Method**: Interact with villager workstations for optimization
4. **Command-Based**: Use commands to optimize villagers in bulk

### 🌍 Cross-Version Support
- **Minecraft 26.1.x**: Built and tested against the current stable Paper API
- **Folia Ready**: Fully compatible with Folia's regionized multithreading
- **Paper Optimized**: Built specifically for Paper and its forks

### 📊 Additional Features
- **Multi-Language Support**: English, German, Italian, Korean, Russian, Chinese
- **Adventure API**: Modern text formatting with MiniMessage support
- **bStats Integration**: Optional anonymous usage statistics
- **Trade Restocking**: Configurable automatic trade restocking
- **Permission System**: Granular permission control for all features

## 📋 Requirements

- **Minecraft Version**: 26.1.2
- **Server Software**: Paper, Folia, Purpur, or a compatible Paper fork
- **Java Version**: Java 25

## 🔧 Installation

1. Download the latest version from [Spigot](https://www.spigotmc.org/) or [Modrinth](https://modrinth.com/)
2. Place the `BubbleVillagers-X.X.X.jar` file in your server's `plugins` folder
3. Restart your server or use a plugin manager to load it
4. Configure the plugin in `plugins/BubbleVillagers/config.yml`
5. Reload with `/bubblevillagers reload` or restart the server

## 📝 Commands

| Command | Description | Permission | Aliases |
|---------|-------------|------------|---------|
| `/villageroptimizer [reload\|version\|disable]` | Admin commands | `bubblevillagers.admin` | `/vo`, `/bv`, `/bubblevillagers` |
| `/optimizevillagers <radius>` | Optimize villagers in radius | `bubblevillagers.optimize` | `/optvils`, `/noai`, `/bvopt` |
| `/unoptimizevillagers <radius>` | Unoptimize villagers in radius | `bubblevillagers.unoptimize` | `/unoptvils`, `/noaiundo`, `/bvunopt` |

### Command Examples
```
/optimizevillagers 50          # Optimize all villagers within 50 blocks
/unoptimizevillagers 30        # Restore AI to villagers within 30 blocks
/bubblevillagers reload        # Reload plugin configuration
/bubblevillagers version       # Check plugin version
```

## 🔐 Permissions

| Permission | Description | Default |
|------------|-------------|---------|
| `bubblevillagers.*` | Access to all features | op |
| `bubblevillagers.admin` | Admin commands (reload, version, disable) | op |
| `bubblevillagers.optimize` | Use optimization commands | op |
| `bubblevillagers.unoptimize` | Use unoptimization commands | op |

### Legacy Permissions
The plugin maintains backward compatibility with VillagerOptimizer permissions:
- `villageroptimizer.admin`
- `villageroptimizer.optimize`
- `villageroptimizer.unoptimize`

## ⚙️ Configuration

The plugin offers extensive configuration options:

```yaml
# Performance Settings
optimization:
  remove-ai: true                    # Remove AI from optimized villagers
  allow-restocking: true             # Allow trade restocking when optimized
  restock-interval: 1200             # Ticks between restocks (20 = 1 second)

# Cooldown Settings
cooldowns:
  optimization-cooldown: 300         # Seconds between optimizations
  command-cooldown: 30               # Seconds between command uses

# Method Settings
methods:
  nametag-enabled: true              # Enable nametag optimization
  block-enabled: true                # Enable block interaction optimization
  workstation-enabled: true          # Enable workstation optimization
  
# Radius Limits
limits:
  max-radius: 100                    # Maximum radius for commands
  
# Language
language: en_us                      # Available: en_us, de_de, it_it, ko_kr, ru_ru, zh_cn
```

## 🌐 Supported Languages

- 🇺🇸 English (en_us)
- 🇩🇪 German (de_de)
- 🇮🇹 Italian (it_it)
- 🇰🇷 Korean (ko_kr)
- 🇷🇺 Russian (ru_ru)
- 🇨🇳 Chinese (zh_cn)

## 🔄 Migration from VillagerOptimizer

BubbleVillagers is fully backward compatible with VillagerOptimizer. Simply:
1. Stop your server
2. Remove the old VillagerOptimizer JAR
3. Add BubbleVillagers JAR
4. Start your server

All configurations and data will be automatically migrated.

## 🐛 Bug Reports & Feature Requests

Found a bug or have a feature request? Please open an issue on our [GitHub Issues](https://github.com/xGinko/VillagerOptimizer/issues) page.

## 📜 License

This project is licensed under the appropriate license - see the LICENSE file for details.

## 🙏 Credits

- **Original Author**: [xGinko](https://github.com/xGinko)
- **Maintainer**: Ξthan
- **Contributors**: See [GitHub Contributors](https://github.com/xGinko/VillagerOptimizer/graphs/contributors)

## 💖 Support

If you enjoy this plugin, please consider:
- ⭐ Starring the [GitHub repository](https://github.com/xGinko/VillagerOptimizer)
- 📝 Leaving a review on Spigot or Modrinth
- 💰 Supporting the developers

---

**Note**: This plugin is designed for performance optimization. Always test on a development server before deploying to production.
